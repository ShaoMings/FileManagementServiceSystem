package com.graduation.controller;

import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import com.graduation.service.PeersService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description 控制层方法基类
 *
 * @author shaoming
 * @date 2021/8/17 15:14
 * @since 1.0
 */
@Controller
public class BaseController {

    @Autowired
    PeersService peersService;

    /**
     * 获取登录用户信息
     *
     * @return 用户
     */
    public User getUser() {
        Subject subject = SecurityUtils.getSubject();
        User user = new User();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        return user;
    }

    /**
     * 获取request对象
     *
     * @return request对象
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取当前用户的集群信息
     *
     * @return 集群对象
     */
    public Peers getPeers() {
        return (Peers) getRequest().getSession().getAttribute("peers");
    }

    /**
     * 获取集群完整管理地址
     *
     * @return 集群完整管理地址
     */
    public String getPeersUrl() {
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        if (peers != null) {
            if (!StringUtils.isBlank(peers.getGroupName())) {
                return peers.getServerAddress() + "/" + peers.getGroupName();
            }
            return peers.getServerAddress();
        }else {
            // 没有选定集群 默认选择第一个
            Integer userPeersId = getUser().getPeersid();
            Peers userPeers = peersService.getById(userPeersId);
            if (!StringUtils.isBlank(userPeers.getGroupName())) {
                getRequest().getSession().setAttribute("peers",userPeers);
                return userPeers.getServerAddress() + "/" + userPeers.getGroupName();
            }
            return userPeers.getServerAddress();
        }
    }

    /**
     * 获取组名
     *
     * @return 组名
     */
    public String getPeersGroupName() {
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        return peers.getGroupName();
    }

    /**
     * 获取访问域名
     *
     * @return 访问域名
     */
    public String getBackUrl() {
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        String showAddress = "";
        if (StringUtils.isBlank(peers.getShowAddress())) {
            if (StringUtils.isBlank(peers.getGroupName())) {
                showAddress = peers.getServerAddress();
            } else {
                showAddress = peers.getServerAddress() + "/" + peers.getGroupName();
            }
        } else {
            if (StringUtils.isBlank(peers.getGroupName())) {
                showAddress = peers.getShowAddress();
            } else {
                showAddress = peers.getShowAddress() + "/" + peers.getGroupName();
            }
        }
        return showAddress;
    }


    /**
     * 获取回显域名(不带url)
     *
     * @return 回显域名(不带url)
     */
    public String getUploadShowUrl() {
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        String showAddress = "";
        if (StringUtils.isBlank(peers.getShowAddress())) {
            showAddress += peers.getServerAddress();
        } else {
            showAddress += peers.getShowAddress();
        }
        return showAddress;
    }

}
