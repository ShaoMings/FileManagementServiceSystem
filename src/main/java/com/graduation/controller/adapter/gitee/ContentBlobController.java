package com.graduation.controller.adapter.gitee;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.graduation.controller.BaseController;
import com.graduation.model.dto.gitee.response.SingleFileResultDto;
import com.graduation.model.dto.gitee.response.TreeDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description 仓库内容  文件内容控制器
 *
 * @author shaoming
 * @date 2021/10/16 15:02
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class ContentBlobController extends BaseController {

    @Autowired
    private GiteeAdapter giteeAdapter;

    @RequestMapping("/blob")
    public void getBlobContent(String path, String repo, String code, HttpServletResponse response) throws IOException {
        path = "/" + getUser().getUsername() + "/" + repo + path;
        TreeDto dto = giteeAdapter.getFile(path, TreeDto.class);
        String request = "https://gitee.com/api/v5/repos/" + dto.getOwner() + "/"
                + repo + "/git/blobs/" + dto.getSha() + "?access_token=" + code;
        String json = HttpUtil.get(request);
        SingleFileResultDto resultDto = JSONUtil.toBean(json, SingleFileResultDto.class);
        String content = resultDto.getContent();
        if (StringUtils.isNotBlank(content)) {
            byte[] bytes = Base64.decode(content);
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=" + dto.getName());
            out.write(bytes);
            out.flush();
        }
    }

    /**
     *  添加文件 传入的path 有 /
     * @param path 要添加的文件的文件路径 包含文件名
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/addFile")
    public FileResponseVo addFile(MultipartFile file,String path, String repo, String token) throws IOException {
        String filename = file.getOriginalFilename();
        String content = Base64.encode(file.getInputStream());
        Map<String,Object> params = new HashMap<>(8);
        String owner = giteeAdapter.getOwnerByToken(token);
        params.put("user",getUser().getUsername());
        params.put("repo",repo);
        params.put("path",path+"/"+filename);
        params.put("owner",owner);
        params.put("content",content);
        params.put("access_token",token);
        params.put("message","添加文件:"+filename+" "+ DateConverter.getNowMonthAndDay());
        String api = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"+path+"/"+filename;
        boolean isAdded = giteeAdapter.addFile(api, params, "POST");
        return isAdded?FileResponseVo.success():FileResponseVo.fail("添加失败!");
    }

    /**
     *  删除文件 传入的path 有 /
     * @param path 要删除的文件路径 包含文件名
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/removeFile")
    public FileResponseVo removeFile(String path, String repo, String token){
        Map<String,Object> params = new HashMap<>(3);
        params.put("user",getUser().getUsername());
        params.put("repo",repo);
        params.put("token",token);
        boolean isRemoved = giteeAdapter.deleteFile(path, params);
        return isRemoved?FileResponseVo.success():FileResponseVo.fail("删除失败!");
    }
}
