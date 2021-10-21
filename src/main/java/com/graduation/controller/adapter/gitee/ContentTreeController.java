package com.graduation.controller.adapter.gitee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.graduation.model.dto.gitee.request.ContentTreeDto;
import com.graduation.model.dto.gitee.response.FileTreeDto;
import com.graduation.model.dto.gitee.response.TreeDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.FileListInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 仓库内容  文件夹内容控制器
 *
 * @author shaoming
 * @date 2021/10/15 16:16
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class ContentTreeController {

    /**
     * 用于获取指定路径下的文件或文件夹信息
     * @param dto 数据传输对象封装类
     * @return 响应对象
     */
    @RequestMapping("/trees")
    public FileResponseVo getRepoTree(ContentTreeDto dto){
        boolean hasToken = true;
        if (StringUtils.isBlank(dto.getAccess_token())){
            hasToken = false;
        }
        String lastPath = "";
        if (StringUtils.isNotBlank(dto.getPath())){
            lastPath = dto.getPath();
        }
        String request = "https://gitee.com/api/v5/repos/" + dto.getOwner()+"/" +
                dto.getRepo() + "/git/trees/"+dto.getSha()+ (hasToken?"?access_token="+dto.getAccess_token():"");
        String json = HttpUtil.get(request);
        FileTreeDto fileTreeDto;
        try {
             fileTreeDto = JSONUtil.toBean(json, FileTreeDto.class);
        }catch (JSONException e){
            return FileResponseVo.fail("获取失败!");
        }
        if (fileTreeDto !=null){
            List<TreeDto> tree = fileTreeDto.getTree();
            List<FileListInfoVo> list = new ArrayList<>();
            List<FileListInfoVo> files = new ArrayList<>();
            List<FileListInfoVo> dirs = new ArrayList<>();
            String finalLastPath = lastPath;
            if (tree !=null){
                tree.forEach(t->{
                    FileListInfoVo fileInfoVo = new FileListInfoVo(finalLastPath +t.getPath(),t.getName(),t.getType()
                            ,t.getFile_size(),t.getSha(),t.getIs_dir());
                    if (t.getIs_dir()){
                        dirs.add(fileInfoVo);
                    }else {
                        files.add(fileInfoVo);
                    }
                });
                list.addAll(dirs);
                list.addAll(files);
                return FileResponseVo.success(dto.getSha(),list);
            }else {
                return FileResponseVo.fail("token is overdue!");
            }
        }
        return FileResponseVo.fail("获取失败!");
    }

}
