package com.graduation.interceptor;

import com.graduation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description 登录拦截规则配置类
 *
 * @author shaoming
 * @date 2021/8/17 10:03
 * @since 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Boolean isInstall = (Boolean) session.getAttribute("isInstall");
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if (isInstall == null){
            if (userService.list().size()<1){
                response.sendRedirect("/install");
                return false;
            }else {
                session.setAttribute("isInstall",true);
                session.setMaxInactiveInterval(1800);
            }
        }
            if (isLogin!=null && isLogin){
                return true;
            }else {
                response.sendRedirect("/login");
                return false;
            }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
