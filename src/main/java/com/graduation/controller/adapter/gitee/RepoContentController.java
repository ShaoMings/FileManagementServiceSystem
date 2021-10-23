package com.graduation.controller.adapter.gitee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.controller.BaseController;
import com.graduation.model.dto.gitee.request.AllRepoDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.RepoInfoVo;
import com.graduation.model.vo.gitee.RepoSimpleInfoVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.CommonUtils;
import com.graduation.utils.DateConverter;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    private List<RepoInfoVo> getUserAllRepo(AllRepoDto dto) {
        if (StringUtils.isNotBlank(dto.getAccess_token())) {
            String api = "https://gitee.com/api/v5/user/repos";
            Map<String, Object> params = new HashMap<>(4);
            params.put("access_token", dto.getAccess_token());
            params.put("sort", (dto.getSort() == null ? "full_name" : dto.getSort()));
            params.put("page", (dto.getPage() == null ? "1" : dto.getPage()));
            params.put("pre_page", (dto.getPer_page() == null ? "20" : dto.getPer_page()));
            String json = HttpUtil.get(api, params);
            if (StringUtils.isNotBlank(json)) {
                JSONArray jsonArray = JSONUtil.parseArray(json);
                JSONObject obj;
                List<RepoInfoVo> list = new ArrayList<>();
                for (Object o : jsonArray) {
                    obj = (JSONObject) o;
                    list.add(new RepoInfoVo(
                            obj.getStr("name"), obj.getStr("path"), obj.getStr("full_name"),
                            obj.getStr("project_creator"), obj.getStr("html_url"), obj.getStr("description"),
                            obj.getBool("public"), obj.getStr("default_branch"), DateConverter.getFormatDate(obj.getDate("created_at")),
                            DateConverter.getFormatDate(obj.getDate("pushed_at")), DateConverter.getFormatDate(obj.getDate("updated_at"))));
                }
                return list;
            }
        }
        return null;
    }

    /**
     * 初始化某个项目仓库
     * @param token token
     * @param owner 仓库所属空间地址 远程用户名
     * @param repo 项目仓库
     * @return 是否初始化成功
     */
    boolean initializeRepository(String token, String owner, String repo){
        String api = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/git/trees/master";
        Map<String, Object> params = new HashMap<>(2);
        params.put("access_token", token);
        params.put("recursive", 1);
        params.put("owner", owner);
        params.put("repo", repo);
        String repository = getUser().getUsername()+"/"+ repo;
        return giteeAdapter.initializeRepository(repository,api,params,"GET");
    }

    /**
     * 将path 转为 repo name
     * @param repos paths
     * @return repo names
     */
    private List<RepoSimpleInfoVo> convertRepoNames(List<String> repos,String owner){
        List<RepoSimpleInfoVo> list = new ArrayList<>();
        repos.forEach(r->{
            if (r.contains("-")) {
                String[] split = r.split("-");
                StringBuilder sb = new StringBuilder();
                for (String s : split) {
                    sb.append(CommonUtils.toUpperFirstChar(s));
                }
                list.add(new RepoSimpleInfoVo(owner, sb.toString(), r));
            }else {
                list.add(new RepoSimpleInfoVo(owner, r, r));
            }
        });
        return list;
    }

    @RequestMapping("/allRepoName")
    public FileResponseVo getUserAllRepoName(AllRepoDto dto) {
        if (StringUtils.isNotBlank(dto.getAccess_token())) {
            String username = getUser().getUsername();
            if (redisUtils.hasKey(username + "-auth_token")) {
                String token = (String) redisUtils.get(username + "-auth_token");
                if (dto.getAccess_token().equals(token)) {
                    List<String> names = giteeAdapter.getAllRepoNames(username);
                    if (names!=null){
                        StringBuilder sb = new StringBuilder();
                        String owner = giteeAdapter.getOwnerByToken(token);
                        Map<String, Object> params = new HashMap<>(2);
                        params.put("access_token",token);
                        params.put("limit",1);
                        names.forEach(n->{
                            sb.delete(0,sb.length());
                            sb.append("https://gitee.com/api/v5/repos/").append(owner)
                                    .append("/").append(n).append("/events");
                            boolean isNoLast = giteeAdapter.isNoTheLast(sb.toString(), params, "GET"
                                    ,giteeAdapter.getTheLastOnRepo(username,n));
                            // 有更新 需要重新拉取
                            if (isNoLast){
                                initializeRepository(token,owner,n);
                            }
                        });
                        return FileResponseVo.success(convertRepoNames(names,owner));
                    }else {
                        List<RepoInfoVo> list = getUserAllRepo(dto);
                        List<RepoSimpleInfoVo> res = new ArrayList<>();
                        assert list != null;
                        list.forEach(r -> {
                            String owner = r.getProjectCreator();
                            String repo = r.getPath();
                            initializeRepository(token,owner,repo);
                            res.add(new RepoSimpleInfoVo(owner, r.getName(), repo));
                        });
                        return FileResponseVo.success(res);
                    }
                }
            }
        }
        return FileResponseVo.fail("error");
    }
}
