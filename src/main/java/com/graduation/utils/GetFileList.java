package com.graduation.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.controller.adapter.local.BaseController;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/16 17:10
 * @since 1.0
 */
@Component
public class GetFileList extends BaseController {



    @Autowired
    private FileService fileService;

    /**
     * 用于获取文件的列表
     *
     * @param backUrl       回调url  入参相当于ip:port/group  首次在根目录获取文件列表时为组名
     * @param serverAddress 服务地址 入参相当于ip:port/group
     * @param dir           要获取的目录 其中根目录为null   可以做持久化处理
     * @return 文件信息列表对象
     */
    public List<FileInfoVo> getDirectoryOrFileList(String backUrl, String serverAddress, String dir) {
        HashMap<String, Object> param = new HashMap<>(13);
        if (StrUtil.isNotBlank(dir)) {
            param.put("dir", dir);
        }
        String username;
        if (dir.contains("/")){
            username = dir.substring(0,dir.indexOf("/"));
        }else {
            username = dir;
        }
        String result = HttpUtil.post(serverAddress + Constant.API_LIST_DIR, param);
        JSONObject parseObj = JSONUtil.parseObj(result);
        ArrayList<FileInfoVo> dirs = new ArrayList<>();
        ArrayList<FileInfoVo> files = new ArrayList<>();
        ArrayList<FileInfoVo> list = new ArrayList<>();
        if ("".equals(parseObj.getStr(Constant.PARAM_KEY_MSG)) && StrUtil.isNotBlank(parseObj.getStr(Constant.PARAM_KEY_DATA))) {
            JSONArray array = parseObj.getJSONArray(Constant.PARAM_KEY_DATA);
            for (int i = 0; i < array.size(); i++) {
                FileInfoVo fileInfoVo = new FileInfoVo();
                JSONObject file = array.getJSONObject(i);
                // 大文件断点续传  后缀之前有 _big  _tmp为临时文件
                if ("_big".equals(file.getStr("name")) || "_tmp".equals(file.getStr("name"))) {
                    continue;
                }
                fileInfoVo.setMd5(file.getStr("md5"));
                String backPath = file.getStr("path");
                if (backPath.contains(username + "/")){
                    backPath = backPath.replace(username + "/","");
                }else {
                    backPath = backPath.replace(username,"");
                }
                fileInfoVo.setPath(backPath);
                fileInfoVo.setName(file.getStr("name"));
                fileInfoVo.setIs_dir(file.getBool("is_dir"));
                fileInfoVo.setPeerAddr(backUrl);
                // 如果是文件夹
                if (file.getBool("is_dir")) {
                    dirs.add(fileInfoVo);
                    fileInfoVo.setSize("0");
                } else {
                    String filePath;
                    if ("".equals(backPath)){
                        filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/"  + fileInfoVo.getName();
                    }else {
                        filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + backPath + "/" + fileInfoVo.getName();
                    }
                    Integer open = fileService.getOpenStatusByFilePath(filePath);
                    fileInfoVo.setOpen(open);
                    fileInfoVo.setSize(FileSizeConverter.getLength(Long.parseLong(file.getStr("size"))));
                    files.add(fileInfoVo);
                }
                fileInfoVo.setMTime(DateConverter.timeStampToDate(file.getStr("mtime"), null));
            }
        }
        list.addAll(dirs);
        list.addAll(files);
        return list;
    }
}
