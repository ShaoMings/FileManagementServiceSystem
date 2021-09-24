package com.graduation.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.graduation.model.pojo.File;
import com.graduation.mapper.FileMapper;
import com.graduation.model.pojo.UserFile;
import com.graduation.model.vo.FileDetailsVo;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.ConvertVo;
import com.graduation.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.service.ShareService;
import com.graduation.service.UserFileService;
import com.graduation.service.UserService;
import com.graduation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private UserFileService userFileService;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private GetFileList getFileList;

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserService userService;

    @Override
    public List<FileInfoVo> getParentFile(String peersGroupName, String serverAddress,String userPath) {
        return getFileList.getDirectoryOrFileList(peersGroupName, serverAddress, userPath);
    }

    @Override
    public List<FileInfoVo> getDirFile(String backUrl, String serverAddress, String dir) {
        return getFileList.getDirectoryOrFileList(backUrl, serverAddress, dir);
    }

    @Override
    public boolean deleteFile(String peersUrl, String groupName, String path, boolean isUpdatePath) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("path", path);
        param.put("auth_token", TokenUtils.getAuthToken(AesUtils.getCheckCodeByDecryptStr(path)));
        String filename = path.substring(path.lastIndexOf("/") + 1);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_DELETE, param));
        boolean isDeleted = Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr("status"));
        if (isDeleted && !isUpdatePath) {
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq("file_name", filename);
            String sqlFilePath = "/" + groupName + "/" + path;
            fileQueryWrapper.likeRight("file_path", sqlFilePath);
            File file = this.getOne(fileQueryWrapper);
            if (file != null) {
                Integer fileId = file.getId();
                if (file.getOpen() == 1) {
                    shareService.privateFileToRemoveRecordByFileId(fileId);
                }
                QueryWrapper<UserFile> userFileQueryWrapper = new QueryWrapper<>();
                userFileQueryWrapper.eq("file_id", fileId);
                boolean removeFile = this.removeById(fileId);
                boolean removeUserFile = userFileService.remove(userFileQueryWrapper);
                return removeFile && removeUserFile;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean mkdir(String serverAddress, String path) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("path", path);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(serverAddress + Constant.API_MKDIR, param));
        return Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT));
    }

    @Override
    public boolean deleteDir(String peersUrl, String path) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("path", path);
        String groupName = peersUrl.substring(peersUrl.lastIndexOf("/"));
        String pathPrefix = groupName + "/" + path;
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_REMOVE_DIR, param));
        boolean isDeleteDir = Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT));
        if (isDeleteDir) {
            return deleteDirFileRecord(pathPrefix);
        }
        return false;
    }

    @Override
    public boolean deleteDirFileRecord(String pathPrefix) {
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.likeRight("file_path", pathPrefix);
        List<File> deleteFileList = this.list(fileQueryWrapper);
        if (deleteFileList.size()>0){
            List<Integer> openFilesIdList = new ArrayList<>();
            List<Integer> fileIds = new ArrayList<>();
            QueryWrapper<UserFile> userFileQueryWrapper = new QueryWrapper<>();
            deleteFileList.forEach(e -> {
                fileIds.add(e.getId());
                if (e.getOpen() == 1){
                    openFilesIdList.add(e.getId());
                }
            });
            if (openFilesIdList.size()>0){
                shareService.privateFilesToRemoveRecordsByFileIdList(openFilesIdList);
            }
            userFileQueryWrapper.in("file_id", fileIds);
            boolean isDeleteFiles = this.removeByIds(fileIds);
            boolean isDeleteUserFiles = userFileService.remove(userFileQueryWrapper);
            return isDeleteFiles && isDeleteUserFiles;
        }else {
            // 空文件夹
            return true;
        }
    }

    @Override
    public boolean renameFileOrFolder(String peersUrl, String oldPath, String newPath, String path, String groupName, String md5) {
        String prefix = "/" + groupName + "/" + path;
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("oldPath", oldPath);
        param.put("newPath", newPath);
        param.put("md5", md5);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_RENAME, param));
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            String filename = newPath.substring(newPath.lastIndexOf("/") + 1);
            if (!"".equals(md5)) {
                String oldFilename = oldPath.substring(oldPath.lastIndexOf("/") + 1);
                String tmp = prefix.replaceFirst("files/", "");
                if (tmp.endsWith("/")){
                    prefix = tmp +  oldFilename;
                }else {
                    prefix = tmp + "/" + oldFilename;
                }
                return fileMapper.updateFilePathString(prefix, oldPath.replace(path , ""), newPath.replace(path, ""), filename) > 0;
            } else {
                return fileMapper.updatePathString(prefix.replaceFirst("files/", ""), oldPath.replace(path , ""), newPath.replace(path, "")) > 0;
            }
        }
        return false;
    }


    @Override
    public FileResponseVo getFileDetails(String peersUrl, String md5) {
        HashMap<String, Object> param = new HashMap<>(8);
        param.put("md5", md5);
        JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_GET_FILE_INFO, param));
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            FileDetailsVo detailsVo = JSONUtil.toBean(jsonObject.getStr("data"), FileDetailsVo.class);
            detailsVo.setSize(FileSizeConverter.getLength(Long.parseLong(detailsVo.getSize())));
            detailsVo.setTimeStamp(DateConverter.getFormatDate(new Date(Long.parseLong(detailsVo.getTimeStamp()) * 1000)));
            if ("".equals(detailsVo.getPath())){
                detailsVo.setUrl(peersUrl + "/"+ detailsVo.getName());
            }else {
                detailsVo.setUrl(peersUrl + "/" + detailsVo.getPath().replace("files/", "") + "/" + detailsVo.getName());
            }
            return FileResponseVo.success(detailsVo);
        }
        return FileResponseVo.fail("获取文件信息失败");
    }

    @Override
    public boolean saveFilePathByUserId(Integer id, String filePath,Integer peerId,String md5) {
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        String tmpPath = filePath.substring(0,filePath.lastIndexOf("/")+1);
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.eq("file_name",filename);
        if (tmpPath.contains("//")){
            filePath = tmpPath.replaceAll("//","/") + filename;
        }
        fileQueryWrapper.eq("file_path",filePath);
        fileQueryWrapper.eq("peer_id",peerId);
        if (this.list(fileQueryWrapper).size()<=0) {
            File file = new File(id, filename,md5, filePath,new Date(),peerId,null);
            boolean flag1 = this.save(file);
            Integer fileId = file.getId();
            boolean flag2 = userFileService.save(new UserFile(null, id, fileId));
            return flag1 && flag2;
        }
        return true;
    }

    @Override
    public boolean saveBigFileMd5ByFilePath(String md5, String filePath) {
        UpdateWrapper<File> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("file_path",filePath);
        updateWrapper.set("file_md5",md5);
        return this.update(updateWrapper);
    }

    @Override
    public String getFileMd5ByFilePath(String filePath) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path",filePath);
        List<File> fileList = this.list(queryWrapper);
        if (fileList.size()>0){
            File file = fileList.get(0);
            return file.getFileMd5();
        }
        return null;
    }

    @Override
    public String getFileMd5ByFileId(Integer fileId) {
        return this.getById(fileId).getFileMd5();
    }


    @Override
    public List<FileInfoVo> getFileInfoListByFileKeyword(String serverAddress, String keyword) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("file_name", keyword);
        List<File> list = this.list(queryWrapper);
        List<FileInfoVo> fileInfoVos = new ArrayList<>();
        list.forEach(e -> {
            String tmpPath = e.getFilePath().replace("/group1/", "");
            String path = tmpPath.substring(0, tmpPath.lastIndexOf("/"));
            String filename = e.getFileName();
            List<FileInfoVo> fileList = FileUtils.getFileList(serverAddress, path, filename);
            fileInfoVos.addAll(fileList);
        });
        return fileInfoVos;
    }

    @Override
    public Integer getFileIdByFilePath(String filePath) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path",filePath);
        List<File> list = this.list(queryWrapper);
        if (list.size()>0){
            return list.get(0).getId();
        }
        return null;
    }

    @Override
    public Integer getFilePeerIdByFilePath(String filePath) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path",filePath);
        List<File> list = this.list(queryWrapper);
        if (list.size()>0){
            File file = list.get(0);
            return file.getPeerId();
        }
        return null;
    }

    @Override
    public boolean updateConvertRecord(ConvertVo fileInfo, InputStream stream, String newFileName, String oldFileName, String uploadApiUrl) {
        // 将转换后的文件进行重新上传
        boolean isSuccess = FileUtils.uploadFileByInputStream(stream, newFileName,
                fileInfo.getPath(), "default", uploadApiUrl, fileInfo.getPeerAddress());
        // 删除原来的文件并更新记录
        if (isSuccess) {
            boolean isDeleteOld = deleteFile(fileInfo.getPeerAddress(), fileInfo.getPeerGroupName(), fileInfo.getPath() + "/" + oldFileName, true);
            String prefix = "/" + fileInfo.getPeerGroupName() + "/" + fileInfo.getPath() + "/" + oldFileName;
            boolean isUpdateNew = fileMapper.updateConvertFilePathString(prefix, oldFileName, newFileName, newFileName) > 0;
            return isDeleteOld && isUpdateNew;
        }
        return false;
    }

    @Override
    public boolean convertPictureFile(ConvertVo fileInfo,String peersUrl,Integer userId,String username) {
        // 通过下载获取文件的输入流
        InputStream inputStream = FileUtils.getFileDownloadStream(fileInfo.getPath(), fileInfo.getFilename(), fileInfo.getPeerAddress());
        // 是否jpg转png
        if (Constant.PICTURE_TYPE_JPG.equals(fileInfo.getSrcSuffix()) && Constant.PICTURE_TYPE_PNG.equals(fileInfo.getDestSuffix())) {
            // 格式转换为bytes
            byte[] bytes = PictureConverter.jpgToPngBytes(inputStream);
            Long fileSize = (long) bytes.length;
            // 转换后剩余空间是否足够
            if (userService.userUploadFileToUpdateDiskSpace(peersUrl,userId,username,fileSize)) {
                InputStream stream = new ByteArrayInputStream(bytes);
                String uploadApiUrl = fileInfo.getPeerAddress() + Constant.API_UPLOAD;
                String oldFileName = fileInfo.getFilename();
                String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".") + 1) + Constant.PICTURE_TYPE_PNG;
                // 将转换后的文件进行重新上传
                return updateConvertRecord(fileInfo, stream, newFileName, oldFileName, uploadApiUrl);
            }else {
                return false;
            }
            // 是否png转jpg
        } else if (Constant.PICTURE_TYPE_PNG.equals(fileInfo.getSrcSuffix()) && Constant.PICTURE_TYPE_JPG.equals(fileInfo.getDestSuffix())) {
            byte[] bytes = PictureConverter.pngToJpgBytes(inputStream);
            Long fileSize = (long) bytes.length;
            // 转换后剩余空间是否足够
            if (userService.userUploadFileToUpdateDiskSpace(peersUrl,userId,username,fileSize)) {
                InputStream stream = new ByteArrayInputStream(bytes);
                String uploadApiUrl = fileInfo.getPeerAddress() + Constant.API_UPLOAD;
                String oldFileName = fileInfo.getFilename();
                String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".") + 1) + Constant.PICTURE_TYPE_JPG;
                return updateConvertRecord(fileInfo, stream, newFileName, oldFileName, uploadApiUrl);
            }else {
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean convertAudioFile(ConvertVo fileInfo,String peersUrl,Integer userId,String username) {

        // 通过下载获取文件的输入流
        InputStream inputStream = FileUtils.getFileDownloadStream(fileInfo.getPath(), fileInfo.getFilename(), fileInfo.getPeerAddress());
        java.io.File file;
        byte[] bytes = null;
        String outputPath;
        // 是否m4a转mp3
        if (Constant.AUDIO_TYPE_M4A.equals(fileInfo.getSrcSuffix())) {
            outputPath = Constant.OUTPUT_TMP_FILE_PATH+"tmp.m4a";
            file = FileUtils.inputStreamToFile(inputStream, outputPath);
            // 格式转换为bytes
            bytes = AudioConverter.m4aToMp3Bytes(file);
            // 是否wav转mp3
        } else if (Constant.AUDIO_TYPE_WAV.equals(fileInfo.getSrcSuffix())) {
            outputPath = Constant.OUTPUT_TMP_FILE_PATH+"tmp.wav";
            file = FileUtils.inputStreamToFile(inputStream, outputPath);
            bytes = AudioConverter.wavToMp3Bytes(file);
        }
        assert bytes != null;
        Long fileSize = (long) bytes.length;
        // 转换后剩余空间是否足够
        if (userService.userUploadFileToUpdateDiskSpace(peersUrl,userId,username,fileSize)) {
            InputStream stream = new ByteArrayInputStream(bytes);
            String uploadApiUrl = fileInfo.getPeerAddress() + Constant.API_UPLOAD;
            String oldFileName = fileInfo.getFilename();
            String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".") + 1) + Constant.AUDIO_TYPE_MP3;
            return updateConvertRecord(fileInfo, stream, newFileName, oldFileName, uploadApiUrl);
        }else {
            return false;
        }
    }

    @Override
    public boolean convertDocumentFile(ConvertVo fileInfo,String peersUrl,Integer userId,String username) {
        // 通过下载获取文件的输入流
        InputStream inputStream = FileUtils.getFileDownloadStream(fileInfo.getPath(), fileInfo.getFilename(), fileInfo.getPeerAddress());
        java.io.File file;
        byte[] bytes;
        String outputPath;
        outputPath = Constant.OUTPUT_TMP_FILE_PATH + "tmp."+fileInfo.getSrcSuffix();
        file = FileUtils.inputStreamToFile(inputStream,outputPath);
        bytes = DocumentConverter.autoConvertByFileType(file,fileInfo.getSrcSuffix());
        assert bytes != null;
        Long fileSize = (long) bytes.length;
        // 转换后剩余空间是否足够
        if (userService.userUploadFileToUpdateDiskSpace(peersUrl,userId,username,fileSize)) {
            InputStream stream = new ByteArrayInputStream(bytes);
            String uploadApiUrl = fileInfo.getPeerAddress() + Constant.API_UPLOAD;
            String oldFileName = fileInfo.getFilename();
            String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".") + 1) + Constant.DOCUMENT_TYPE_PDF;
            return updateConvertRecord(fileInfo, stream, newFileName, oldFileName, uploadApiUrl);
        }else {
            return false;
        }

    }

    @Override
    public boolean changeOpenStatusById(Integer id, Integer open) {
        UpdateWrapper<File> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("open",open);
        return this.update(updateWrapper);
    }

    @Override
    public Integer getOpenStatusByFilePath(String filePath) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path",filePath);
        List<File> list = this.list(queryWrapper);
        if(list.size()<1){
            return 0;
        }else {
            File file = list.get(0);
            return file.getOpen();
        }

    }

}
