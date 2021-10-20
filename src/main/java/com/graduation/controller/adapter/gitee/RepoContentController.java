package com.graduation.controller.adapter.gitee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.model.dto.gitee.request.AllRepoDto;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.RepoInfoVo;
import com.graduation.model.vo.gitee.RepoSimpleInfoVo;
import com.graduation.utils.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 用于管理仓库的控制器
 *
 * @author shaoming
 * @date 2021/10/18 13:47
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class RepoContentController {

    @RequestMapping("allRepo")
    public FileResponseVo getUserAllRepo(AllRepoDto dto){
         if (StringUtils.isNotBlank(dto.getAccess_token())){
             String request = "https://gitee.com/api/v5/user/repos?access_token="
                     +dto.getAccess_token()+"&sort="+(dto.getSort() == null?"full_name":dto.getSort())
                     +"&page="+(dto.getPage() == null?"1":dto.getPage())
                     +"&pre_page="+(dto.getPer_page() == null?"20":dto.getPer_page());
             String json = HttpUtil.get(request);
             JSONArray jsonArray = JSONUtil.parseArray(json);
             JSONObject obj;
             List<RepoInfoVo> list = new ArrayList<>();
             for (Object o : jsonArray) {
                 obj = (JSONObject) o;
                 list.add(new RepoInfoVo(
                         obj.getStr("name"),obj.getStr("path"),obj.getStr("full_name"),
                         obj.getStr("project_creator"),obj.getStr("html_url"),obj.getStr("description"),
                         obj.getBool("public"),obj.getStr("default_branch"), DateConverter.getFormatDate(obj.getDate("created_at")),
                         DateConverter.getFormatDate(obj.getDate("pushed_at")),DateConverter.getFormatDate(obj.getDate("updated_at"))));
             }
             return FileResponseVo.success(list);
         }
         return FileResponseVo.fail("error");
    }

    @RequestMapping("/allRepoName")
    public FileResponseVo getUserAllRepoName(AllRepoDto dto){
        if (StringUtils.isNotBlank(dto.getAccess_token())){
            String request = "https://gitee.com/api/v5/user/repos?access_token="
                    +dto.getAccess_token()+"&sort="+(dto.getSort() == null?"full_name":dto.getSort())
                    +"&page="+(dto.getPage() == null?"1":dto.getPage())
                    +"&pre_page="+(dto.getPer_page() == null?"20":dto.getPer_page());
            String json = HttpUtil.get(request);
            JSONArray jsonArray = JSONUtil.parseArray(json);
            JSONObject obj;
            List<RepoSimpleInfoVo> list = new ArrayList<>();
            for (Object o : jsonArray) {
                obj = (JSONObject) o;
                list.add(new RepoSimpleInfoVo(
                        obj.getStr("full_name").substring(0,obj.getStr("full_name").indexOf("/")),
                        obj.getStr("name"),
                        obj.getStr("path")));
            }
            return FileResponseVo.success(list);
        }
        return FileResponseVo.fail("error");
    }
}
