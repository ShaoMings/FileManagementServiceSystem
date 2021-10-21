package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.model.dto.gitee.request.AllRepoDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.RepoInfoVo;
import com.graduation.model.vo.gitee.RepoSimpleInfoVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 用于管理仓库的控制器
 *
 * @author shaoming
 * @date 2021/10/18 13:47
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class RepoContentController extends BaseController {

    @Autowired
    private GiteeAdapter giteeAdapter;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/allRepo")
    public FileResponseVo getUserAllRepo(AllRepoDto dto){
        if (StringUtils.isNotBlank(dto.getAccess_token())){
            String api = "https://gitee.com/api/v5/user/repos";
            Map<String, Object> params = new HashMap<>(4);
            params.put("access_token",dto.getAccess_token());
            params.put("sort",(dto.getSort() == null?"full_name":dto.getSort()));
            params.put("page",(dto.getPage() == null?"1":dto.getPage()));
            params.put("pre_page",(dto.getPer_page() == null?"20":dto.getPer_page()));
            List<RepoInfoVo> list = giteeAdapter.initializeRepository(getUser().getUsername(), api, params, "GET");
            return FileResponseVo.success(list);
        }
         return FileResponseVo.fail("error");
    }

    @RequestMapping("/allRepoName")
    @SuppressWarnings("unchecked")
    public FileResponseVo getUserAllRepoName(AllRepoDto dto){
        if (StringUtils.isNotBlank(dto.getAccess_token())){
            if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")){
                String token = (String) redisUtils.get(getUser().getUsername() + "-auth_token");
                if (dto.getAccess_token().equals(token)){
                    List<RepoInfoVo> list = (List<RepoInfoVo>) getUserAllRepo(dto).getData();
                    List<RepoSimpleInfoVo> res = new ArrayList<>();
                    list.forEach(r-> res.add(new RepoSimpleInfoVo(r.getProjectCreator(),r.getName(),r.getPath())));
                    return FileResponseVo.success(res);
                }
            }
        }
        return FileResponseVo.fail("error");
    }
}
