package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.repo.adapter.TokenProxy;
import com.graduation.utils.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description 仓库内容  文件夹内容控制器
 *
 * @author shaoming
 * @date 2021/10/15 16:16
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class ContentTreeController extends BaseController {

    @Autowired
    private GiteeAdapter giteeAdapter;

    /**
     * 用于获取指定路径下的文件或文件夹信息
     *
     * @return 响应对象
     */
    @RequestMapping("/trees")
    public FileResponseVo getRepoTree(String path, String repo,String token) {
        if (StringUtils.isNotBlank(token)){
            token = TokenProxy.tokenDecode(token);
        }
        String username = getUser().getUsername();
        List<JcrContentTreeDto> tree = giteeAdapter.getRepoContentTree(username, path, repo, token);
        if (tree!=null){
            return FileResponseVo.success(tree);
        }
        return FileResponseVo.fail("error");
    }

    /**
     * solr关键字检索
     * @param repo 仓库名称
     * @param keywords 关键字
     * @return 响应对象
     */
    @RequestMapping("/search")
    public FileResponseVo search(String repo,String keywords){
        List<JcrContentTreeDto> res = giteeAdapter.searchByKeyWordsAndRepo(repo, keywords);
        if (res!=null){
            return FileResponseVo.success(res);
        }
        return FileResponseVo.fail("error");
    }

    /**
     *  添加文件夹 传入的path 有 /
     * @param path 要添加的文件夹路径
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/addDir")
    public FileResponseVo addDir(String path,String repo,String token){
        if (StringUtils.isNotBlank(token)){
            token = TokenProxy.tokenDecode(token);
        }
        String owner = giteeAdapter.getOwnerByToken(token);
        String api = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents"+path+"/.keep";
        path = "/"+getUser().getUsername()+"/"+repo+path;
        Map<String,Object> params = new HashMap<>(3);
        params.put("access_token",token);
        params.put("content","IA==");
        params.put("message","创建文件夹 "+DateConverter.getNowMonthAndDay());
        boolean isAdded = giteeAdapter.addDirectory(path, api, params, "POST");
        return isAdded?FileResponseVo.success():FileResponseVo.fail("");
    }

    /**
     *  删除文件夹 传入的path 有 /
     * @param path 要删除的文件夹路径
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/removeDir")
    public FileResponseVo removeDir(String path,String repo,String token){
        if (StringUtils.isNotBlank(token)){
            token = TokenProxy.tokenDecode(token);
        }
        Map<String,Object> params = new HashMap<>(3);
        params.put("user",getUser().getUsername());
        params.put("repo",repo);
        params.put("token",token);
        boolean isRemoved = giteeAdapter.removeDirectory(path, params);
        return isRemoved?FileResponseVo.success():FileResponseVo.fail("删除失败!");
    }

}
