package com.graduation.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UploadResultVo;
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Description 文件操作工具类
 *
 * @author shaoming
 * @date 2021/8/16 21:41
 * @since 1.0
 */
public class FileUtils {


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
     * 文件上传操作  采用hutools工具包post上传
     *
     * @param tempPath  临时路径 创建文件对象用
     * @param url  服务器地址
     * @param path  路径
     * @param scene  场景
     * @param multipartFile 文件对象
     * @param backUrl  回调url 用于文件资源访问
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
     * @param multipartFile  文件对象
     * @param path 路径
     * @param backUrl 回调url 用于文件资源访问
     * @return 文件响应对象
     */
    public static FileResponseVo upload(MultipartFile multipartFile, String path, String backUrl) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            // 设置请求对象属性
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000)
                    .setSocketTimeout(200000)
                    .build();
            // 创建post请求对象 并指定请求url
            HttpPost httpPost = new HttpPost(path);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(StandardCharsets.UTF_8)
                    .addTextBody("output", "json")
                    .addBinaryBody("file", multipartFile.getInputStream(),
                            ContentType.DEFAULT_BINARY, multipartFile.getOriginalFilename());
            httpPost.setEntity(multipartEntityBuilder.build());
            httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == Constant.SUCCESS_STATUS_CODE) {
                    String respStr = EntityUtils.toString(httpResponse.getEntity());
                UploadResultVo resultVo = JSONUtil.toBean(respStr, UploadResultVo.class);
                resultVo.setUrl(backUrl+resultVo.getPath());
                return FileResponseVo.success(resultVo);
            }
            httpClient.close();
            httpResponse.close();
            return FileResponseVo.fail("文件上传出错!");
        } catch (Exception e) {
            return FileResponseVo.fail("文件上传出错!");
        }
    }


}
