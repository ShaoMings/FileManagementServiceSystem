package com.graduation.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.ApiResultVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.IndexConfigInfoVo;
import com.graduation.model.vo.InstallVo;
import com.graduation.service.PeersService;
import com.graduation.service.UserService;
import com.graduation.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Objects;

/**
 * Description 初始化系统控制器 包括用户注册  集群信息绑定等
 *
 * @author shaoming
 * @date 2021/8/17 17:05
 * @since 1.0
 */
@Controller
@RequestMapping("/install")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitSystemController {

    @Value("${default.server.address}")
    private String defaultServerAddress;

    @Value("${default.server.group}")
    private String defaultServerGroup;

    private final PeersService peersService;
    private final UserService userService;


    /**
     * 配置集群信息页面
     *
     * @return 页面
     */
    @GetMapping("/")
    public String index() {
        return "install";
    }

    /**
     * 检查本地指定的服务地址是否运行文件服务程序
     *
     * @return 响应对象
     */
    @GetMapping("/checkLocalServer")
    @ResponseBody
    public FileResponseVo checkLocalServer() {
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("action", "get");
        String res;
        try {
            res = HttpRequest.post(defaultServerAddress + "/" + defaultServerGroup
                    + Constant.API_RELOAD).form(map).timeout(1000).execute().body();
        } catch (Exception e) {
            try {
                res = HttpRequest.post(defaultServerAddress
                        + Constant.API_RELOAD).form(map).timeout(1000).execute().body();
            } catch (Exception r) {
                return FileResponseVo.fail("请检查该服务地址是否运行文件服务程序");
            }
        }
        if (StringUtils.isBlank(res)) {
            FileResponseVo.fail("请检查该服务地址是否运行文件服务程序");
        }
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            JSONObject data = jsonObject.getJSONObject("data");
            IndexConfigInfoVo indexConfigInfoVo = JSONUtil.toBean(data, IndexConfigInfoVo.class);
            return FileResponseVo.success("获取信息成功", indexConfigInfoVo);
        }
        return FileResponseVo.fail("服务地址未运行文件服务程序");
    }

    /**
     *  校验服务地址是否能正常连接
     * @param peers 集群对象
     * @param bindingResult 用于接收错误信息 即配合@Validated注解，将不符合要求的参数提示的错误信息进行接收
     * @return 响应对象
     */
    @GetMapping("/checkServer")
    @ResponseBody
    @Validated
    public FileResponseVo checkServer(@Valid Peers peers, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return FileResponseVo.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        if (!Validator.isUrl(peers.getServerAddress())) {
            return FileResponseVo.fail("服务地址不正确!");
        }
        if (StrUtil.isNotBlank(peers.getShowAddress()) && !Validator.isUrl(peers.getShowAddress())) {
            return FileResponseVo.fail("访问域名不正确!");
        }
        try {
            String urlPath = peers.getServerAddress();
            if (StrUtil.isNotBlank(peers.getGroupName())) {
                urlPath += "/" + peers.getGroupName();
            }
            String res = HttpRequest.get(urlPath+Constant.API_STAT).timeout(2000).execute().body();
            ApiResultVo resultVo = JSONUtil.toBean(res, ApiResultVo.class);
            if (!Constant.API_STATUS_SUCCESS.equals(resultVo.getStatus())) {
                return FileResponseVo.fail("连接服务地址失败,检查地址是否正确,是否加入白名单!");
            }
            return FileResponseVo.success("校验通过!");
        }catch (Exception e){
            return FileResponseVo.fail("连接服务地址失败,检查地址是否正确,是否加入白名单!");
        }
    }


    /**
     *  用户注册以及系统信息初始化
     * @param installVo 系统信息vo
     * @param bindingResult 用于接收错误信息 即配合@Validated注解，将不符合要求的参数提示的错误信息进行接收
     * @return 响应对象
     */
    @GetMapping("/doInstall")
    @ResponseBody
    @Validated
    public FileResponseVo doinstall(@Valid InstallVo installVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return FileResponseVo.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Peers peers = installVo.getPeers();
        if (peersService.save(peers)) {
            User user = installVo.getUser();
            user.setPeersid(peers.getId());
            // 用户注册
            if (userService.save(user)){
                return FileResponseVo.success("信息初始化成功");
            }
            return FileResponseVo.fail("信息初始化失败");
        }
        return FileResponseVo.fail("信息初始化失败");
    }

}
