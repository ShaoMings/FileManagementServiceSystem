package com.graduation.controller.adapter.local;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.model.dto.PageInfoDto;
import com.graduation.model.pojo.Peers;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.PeersService;
import com.graduation.utils.Constant;
import com.graduation.utils.NetUtils;
import com.graduation.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Controller
@RequestMapping("/peers")
public class PeersController extends BaseController {

    @Autowired
    private PeersService peersService;

    @Value("${default.ipaddr}")
    private String address;

    @Value("${default.server.proxy}")
    private String proxy;


    @RequestMapping("/address")
    @ResponseBody
    public String getAddress(){
        if (NetUtils.INTERNET_IP.equals(address)){
            return proxy+"/"+getPeersGroupName();
        }
        return getPeersUrl();
    }

    @RequestMapping("/group")
    @ResponseBody
    public String getGroupName(){
        return getPeersGroupName();
    }


    /**
     * 集群列表页面
     *
     * @return 页面
     */
    @RequestMapping("")
    public String index() {
        return "peers/list";
    }

    /**
     * 添加集群页面
     *
     * @return 页面
     */
    @RequestMapping("/add")
    public String add() {
        return "peers/add";
    }

    /**
     * 集群编辑页面
     *
     * @param id    集群id
     * @param model model对象
     * @return 页面
     */
    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Peers peers = peersService.getById(id);
        model.addAttribute("peers", peers);
        return "peers/edit";
    }


    /**
     * 添加集群
     *
     * @param peers         集群
     * @param bindingResult 错误结果集对象
     * @return 响应对象
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public FileResponseVo doAdd(@Valid Peers peers, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return FileResponseVo.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        // 校验服务地址
        if (!RegexUtils.verifyUrl(peers.getServerAddress())) {
            return FileResponseVo.fail("服务地址有误,请检查!");
        }
        try {
            String urlPath = peers.getServerAddress();
            if (StrUtil.isNotBlank(peers.getGroupName())) {
                urlPath += "/" + peers.getGroupName();
            }
            String str = HttpRequest.get(urlPath + Constant.API_STAT).timeout(2000).execute().body();
            JSONObject jsonObject = JSONUtil.parseObj(str);
            if (!Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                return FileResponseVo.fail("连接服务地址失败,请检查地址是否正确!");
            }
        } catch (Exception e) {
            return FileResponseVo.fail("连接服务地址失败,请检查地址是否正确!");
        }
        // 校验集群是否已经添加
        if (peersService.checkPeersExist(peers.getServerAddress())) {
            return FileResponseVo.fail("该集群已存在!");
        }
        //不存在则添加集群
        if (peersService.addPeers(peers)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("系统出现异常!");
    }

    /**
     * 删除集群
     *
     * @param id 集群id
     * @return 响应对象
     */
    @RequestMapping("/del")
    @ResponseBody
    public FileResponseVo deletePeersById(int id) {
        if (getPeers().getId() == id) {
            return FileResponseVo.fail("删除集群失败,该集群使用中,请稍后再试!");
        }
        if (peersService.deletePeersById(id)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除集群失败!");
    }


    @RequestMapping("/doEdit")
    @ResponseBody
    public FileResponseVo doEdit(@Valid Peers peers, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return FileResponseVo.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        // 校验服务地址
        if (!RegexUtils.verifyUrl(peers.getServerAddress())) {
            return FileResponseVo.fail("服务地址有误,请检查!");
        }
        try {
            String urlPath = peers.getServerAddress();
            if (StrUtil.isNotBlank(peers.getGroupName())) {
                urlPath += "/" + peers.getGroupName();
            }
            String str = HttpUtil.get(urlPath + Constant.API_STAT);
            JSONObject jsonObject = JSONUtil.parseObj(str);
            if (!Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                return FileResponseVo.fail("连接服务地址失败,请检查地址是否正确!");
            }
        } catch (Exception e) {
            return FileResponseVo.fail("连接服务地址失败,请检查地址是否正确!");
        }
        // 校验集群是否添加
        if (!peersService.getById(peers.getId()).getServerAddress().equals(peers.getServerAddress())
                && peersService.checkPeersExist(peers.getServerAddress())) {
            return FileResponseVo.fail("该集群已存在!");
        }
        // 更新集群信息
        if (peersService.updateById(peers)){
            if (getPeers().getId().equals(peers.getId())){
                session.setAttribute("peers",peersService.getById(peers.getId()));
            }
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("系统发生异常");
    }


    /**
     * 集群列表分页
     *
     * @param page  当前页码
     * @param limit 显示记录条数
     * @return 分页对象
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageInfoDto<Peers> page(int page, int limit) {
        return peersService.listPage(page, limit);
    }


}

