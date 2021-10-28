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



    @RequestMapping("/allRepoName")
    public FileResponseVo getUserAllRepoName(AllRepoDto dto) {
        List<RepoSimpleInfoVo> allRepoInfo = giteeAdapter.getAllRepoInfo(getUser().getUsername(), dto);
        if (allRepoInfo!=null){
            return FileResponseVo.success(allRepoInfo);
        }
        return FileResponseVo.fail("error");
    }
}
