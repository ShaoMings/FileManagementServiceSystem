package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.repo.adapter.GiteeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ContentTreeController extends BaseController {

    @Autowired
    private GiteeAdapter giteeAdapter;

    /**
     * 用于获取指定路径下的文件或文件夹信息
     *
     * @return 响应对象
     */
    @RequestMapping("/trees")
    public FileResponseVo getRepoTree(String path, String repo) {
        String username = getUser().getUsername();
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
