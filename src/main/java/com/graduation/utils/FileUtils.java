package com.graduation.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.exception.FileConverterException;
import com.graduation.exception.FileDownloadException;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UploadResultVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description 文件操作工具类
 *
 * @author shaoming
 * @date 2021/8/16 21:41
 * @since 1.0
 */
public class FileUtils {

    private static final String PARAM_KEY_MSG = "message";
    private static final String PARAM_KEY_DATA = "data";


    /**
     * 用于将 multipartFile对象转为File对象
     *
     * @param multipartFile 多部分文件  (一般为前端传过来的文件流对象)
     * @param tempPath      临时路径
     * @return File对象
     */
    private static File multipartFileToFile(MultipartFile multipartFile, String tempPath) {
        // 获得文件原本的文件名
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(tempPath + fileName);
        try {
            // 获取文件绝对路径 包括完整文件名 然后进行转换
            multipartFile.transferTo(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 将inputStream转为file对象
     *
     * @param inputStream 输入流
     * @param outputPath 输出文件路径
     * @return file对象
     */
    public static File inputStreamToFile(InputStream inputStream, String outputPath) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputPath);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            return new File(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileConverterException("输入流转文件失败!");
    }


    /**
     * 文件上传操作  采用hutools工具包post上传
     *
     * @param tempPath      临时路径 创建文件对象用
     * @param url           服务器地址
     * @param path          路径
     * @param scene         场景
     * @param multipartFile 文件对象
     * @param backUrl       回调url 用于文件资源访问
     * @return 文件响应对象
     */
    public static FileResponseVo upload(String tempPath, String url, String path,
                                        String scene, MultipartFile multipartFile, String backUrl) {
        File parentFile = new File(tempPath);
        // 对象存在
        if (parentFile.exists()) {
            // 不是目录
            if (!parentFile.isDirectory()) {
                return FileResponseVo.fail("该临时目录不是一个文件夹");
            }
        } else {
            // 不存在 创建该目录
            parentFile.mkdir();
        }
        File file = FileUtils.multipartFileToFile(multipartFile, tempPath);
        HashMap<String, Object> map = new HashMap<>();
        map.put("output", "json");
        map.put("path", path);
        map.put("scene", scene);
        map.put("file", file);
        try {
            String result = HttpUtil.post(url, map);
            UploadResultVo resultVo = JSONUtil.toBean(result, UploadResultVo.class);
            resultVo.setUrl(backUrl + resultVo.getPath());
            return FileResponseVo.success(resultVo);
        } catch (Exception e) {
            file.delete();
            return FileResponseVo.fail("文件上传出错!");
        }
    }


    /**
     * 文件上传操作  采用httpClient方式上传文件
     * 为什么要用这个方式?  hutool和okhttp上传大文件都会有内存溢出的报错
     *
     * @param multipartFile 文件对象
     * @param scene         场景
     * @param uploadPath    自定义上传路径 不含文件名
     * @param uploadApiUrl  服务地址/组名/上传文件api
     * @param serverAddress 服务地址     用于拼接为文件资源访问
     * @return 文件响应对象
     */
    public static FileResponseVo upload(MultipartFile multipartFile, String uploadPath, String scene, String uploadApiUrl, String serverAddress) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            // 设置请求对象属性
            if (uploadPath == null) {
                uploadPath = "";
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000)
                    .setSocketTimeout(200000)
                    .build();
            // 创建post请求对象 并指定请求url
            HttpPost httpPost = new HttpPost(uploadApiUrl);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(StandardCharsets.UTF_8)
                    .addTextBody("output", "json")
                    .addTextBody("path", uploadPath)
                    .addTextBody("scene", scene)
                    .addBinaryBody("file", multipartFile.getInputStream(),
                            ContentType.DEFAULT_BINARY, multipartFile.getOriginalFilename());
            httpPost.setEntity(multipartEntityBuilder.build());
            httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == Constant.SUCCESS_STATUS_CODE) {
                String respStr = EntityUtils.toString(httpResponse.getEntity());
                UploadResultVo resultVo = JSONUtil.toBean(respStr, UploadResultVo.class);
                String serverFilePath = resultVo.getPath();
                resultVo.setUrl(serverAddress + serverFilePath);
                // serverFilePath为文件服务器存放的真实路径 前缀有组名 如果设置了组名的话
                return FileResponseVo.success(resultVo);
            }
            httpClient.close();
            httpResponse.close();
            return FileResponseVo.fail("文件上传出错!");
        } catch (Exception e) {
            return FileResponseVo.fail("文件上传出错!");
        }
    }

    /**
     * 通过输入流以及文件名上传文件
     *
     * @param inputStream   文件输入流
     * @param fileName      文件名
     * @param uploadPath    自定义上传路径 不含文件名
     * @param scene         场景
     * @param uploadApiUrl  服务地址/组名/上传文件api
     * @param serverAddress 服务地址     用于拼接为文件资源访问
     * @return 是否上传成功
     */
    public static boolean uploadFileByInputStream(InputStream inputStream, String fileName, String uploadPath, String scene, String uploadApiUrl, String serverAddress) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            // 设置请求对象属性
            if (uploadPath == null) {
                uploadPath = "";
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000)
                    .setSocketTimeout(200000)
                    .build();
            // 创建post请求对象 并指定请求url
            HttpPost httpPost = new HttpPost(uploadApiUrl);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(StandardCharsets.UTF_8)
                    .addTextBody("output", "json")
                    .addTextBody("path", uploadPath)
                    .addTextBody("scene", scene)
                    .addBinaryBody("file", inputStream,
                            ContentType.DEFAULT_BINARY, fileName);
            httpPost.setEntity(multipartEntityBuilder.build());
            httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == Constant.SUCCESS_STATUS_CODE) {
                return true;
            }
            httpClient.close();
            httpResponse.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 用于获取文件的列表
     *
     * @param backUrl       回调url  入参相当于ip:port/group  首次在根目录获取文件列表时为组名
     * @param serverAddress 服务地址 入参相当于ip:port/group
     * @param dir           要获取的目录 其中根目录为null   可以做持久化处理
     * @return 文件信息列表对象
     */
    public static List<FileInfoVo> getDirectoryOrFileList(String backUrl, String serverAddress, String dir) {
        HashMap<String, Object> param = new HashMap<>(13);
        if (StrUtil.isNotBlank(dir)) {
            param.put("dir", dir);
        }
        String result = HttpUtil.post(serverAddress + Constant.API_LIST_DIR, param);
        JSONObject parseObj = JSONUtil.parseObj(result);
        ArrayList<FileInfoVo> files = new ArrayList<>();
        if ("".equals(parseObj.getStr(PARAM_KEY_MSG)) && StrUtil.isNotBlank(parseObj.getStr(PARAM_KEY_DATA))) {
            JSONArray array = parseObj.getJSONArray(PARAM_KEY_DATA);
            for (int i = 0; i < array.size(); i++) {
                FileInfoVo fileInfoVo = new FileInfoVo();
                JSONObject file = array.getJSONObject(i);
                // 备份文件在后缀之前有 _big
                if ("_big".equals(file.getStr("name")) || "_tmp".equals(file.getStr("name"))) {
                    continue;
                }
                fileInfoVo.setMd5(file.getStr("md5"));
                fileInfoVo.setPath(file.getStr("path"));
                fileInfoVo.setName(file.getStr("name"));
                fileInfoVo.setIs_dir(file.getBool("is_dir"));
                fileInfoVo.setPeerAddr(backUrl);
                // 如果是文件夹
                if (file.getBool("is_dir")) {
                    fileInfoVo.setSize("0");
                } else {
                    fileInfoVo.setSize(FileSizeConverter.getLength(Long.parseLong(file.getStr("size"))));
                }
                fileInfoVo.setMTime(DateConverter.timeStampToDate(file.getStr("mtime"), null));
                files.add(fileInfoVo);
            }
        }
        return files;
    }


    /**
     * 用于获取文件
     *
     * @param serverAddress 服务地址 入参相当于ip:port/group
     * @param path          要获取的目录 其中根目录为null   可以做持久化处理
     * @return 文件信息列表对象
     */
    public static List<FileInfoVo> getFileList(String serverAddress, String path, String fileName) {
        HashMap<String, Object> param = new HashMap<>(12);
        if (StrUtil.isNotBlank(path)) {
            param.put("dir", path);
        }
        String result = HttpUtil.post(serverAddress + Constant.API_LIST_DIR, param);
        JSONObject parseObj = JSONUtil.parseObj(result);
        ArrayList<FileInfoVo> files = new ArrayList<>();
        if ("".equals(parseObj.getStr(PARAM_KEY_MSG)) && StrUtil.isNotBlank(parseObj.getStr(PARAM_KEY_DATA))) {
            JSONArray array = parseObj.getJSONArray(PARAM_KEY_DATA);
            for (int i = 0; i < array.size(); i++) {
                FileInfoVo fileInfoVo = new FileInfoVo();
                JSONObject file = array.getJSONObject(i);
                // 备份文件在后缀之前有 _big
                if ("_big".equals(file.getStr("name")) || !fileName.equals(file.getStr("name"))) {
                    continue;
                }
                // 如果是文件夹
                if (file.getBool("is_dir")) {
                    continue;
                } else {
                    fileInfoVo.setSize(FileSizeConverter.getLength(Long.parseLong(file.getStr("size"))));
                }
                fileInfoVo.setMd5(file.getStr("md5"));
                fileInfoVo.setPath(file.getStr("path"));
                fileInfoVo.setName(file.getStr("name"));
                fileInfoVo.setIs_dir(file.getBool("is_dir"));
                fileInfoVo.setPeerAddr(serverAddress);
                fileInfoVo.setMTime(DateConverter.timeStampToDate(file.getStr("mtime"), null));
                files.add(fileInfoVo);
            }
        }
        return files;
    }

    /**
     * 下载文件到 InputStream
     *
     * @param path        文件路径
     * @param name        文件名
     * @param peerAddress 服务集群地址
     * @return InputStream
     */
    public static InputStream getFileDownloadStream(String path, String name, String peerAddress) {
        BufferedInputStream in = null;
        try {
            // 将文件名 encode 确保 new URL 不出错
            String filename = URLEncoder.encode(name, "UTF-8");
            filename = StringUtils.replace(filename, "+", "%20");
            URL url = new URL(peerAddress + "/" + path + "/" + filename);
            in = new BufferedInputStream(url.openStream());
            return in;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileDownloadException("下载文件为输入流出错");
    }


}
