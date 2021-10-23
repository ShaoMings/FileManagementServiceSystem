package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.RedisUtils;
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

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RepoContentController repoContentController;

    /**
     * 用于获取指定路径下的文件或文件夹信息
     *
     * @return 响应对象
     */
    @RequestMapping("/trees")
    public FileResponseVo getRepoTree(String path, String repo,String token) {
        String username = getUser().getUsername();
        if ("".equals(path)){
            StringBuilder sb = new StringBuilder();
            Map<String, Object> params = new HashMap<>(2);
            if (token == null){
                if (redisUtils.hasKey(username + "-auth_token")){
                    token = (String) redisUtils.get(username + "-auth_token");
                }
            }
            String owner = giteeAdapter.getOwnerByToken(token);
            params.put("access_token",token);
            params.put("limit",1);
            sb.append("https://gitee.com/api/v5/repos/").append(owner)
                    .append("/").append(repo).append("/events");
            boolean isNoLast = giteeAdapter.isNoTheLast(sb.toString(), params, "GET"
                    ,giteeAdapter.getTheLastOnRepo(username,repo));
            // 有更新 需要重新拉取
            if (isNoLast){
                repoContentController.initializeRepository(token,owner,repo);
            }
        }
        boolean isParent = "".equals(path) || "/".equals(path);
        String prefix = "/" + username + "/" + repo;
        String origin = prefix;
        if (!isParent){
            prefix += path;
        }
        List<JcrContentTreeDto> tree = giteeAdapter.getDirectoryFiles(prefix);
        tree.forEach(t->{
            t.setPath(t.getPath().replace(origin,""));
        });
        return FileResponseVo.success(tree);
    }

}
