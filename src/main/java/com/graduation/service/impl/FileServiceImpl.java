package com.graduation.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.model.pojo.File;
import com.graduation.mapper.FileMapper;
import com.graduation.model.pojo.UserFile;
import com.graduation.model.vo.FileDetailsVo;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.service.UserFileService;
import com.graduation.utils.Constant;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileSizeConverter;
import com.graduation.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    UserFileService userFileService;

    @Override
    public List<FileInfoVo> getParentFile(String peersGroupName, String serverAddress) {
        return FileUtils.getDirectoryOrFileList(peersGroupName,serverAddress,null);
    }

    @Override
    public List<FileInfoVo> getDirFile(String backUrl, String serverAddress, String dir) {
        return FileUtils.getDirectoryOrFileList(backUrl,serverAddress,dir);
    }

    @Override
    public boolean deleteFile(String peersUrl, String md5) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("md5",md5);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_DELETE, param));
        return Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr("status"));
    }

    @Override
    public boolean deleteDir(String peersUrl,String path) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("path",path);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_REMOVE_DIR, param));
        return Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr("status"));
    }

    @Override
    public FileResponseVo getFileDetails(String peersUrl, String md5) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("md5",md5);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_GET_FILE_INFO, param));
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr("status"))) {
            FileDetailsVo detailsVo = JSONUtil.toBean(jsonObject.getStr("data"), FileDetailsVo.class);
            detailsVo.setSize(FileSizeConverter.getLength(Long.parseLong(detailsVo.getSize())));
            detailsVo.setTimeStamp(DateConverter.getFormatDate(new Date(Long.parseLong(detailsVo.getTimeStamp())*1000)));
            detailsVo.setUrl(peersUrl+"/"+detailsVo.getPath().replace("files/","")+"/"+detailsVo.getName());
            return FileResponseVo.success(detailsVo);
        }
        return FileResponseVo.fail("获取文件信息失败");
    }

    @Override
    public boolean saveFilePathByUserId(Integer id, String filePath) {
        String filename = filePath.substring(filePath.lastIndexOf("/")+1);
        File file = new File(id,filename,filePath);
        boolean flag1 = this.save(file);
        Integer fileId = file.getId();
        boolean flag2 = userFileService.save(new UserFile(null, id, fileId));
        return flag1 && flag2;
    }
}
