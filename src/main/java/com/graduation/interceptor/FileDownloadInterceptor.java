package com.graduation.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description 用于对文件下载请求的合法检验与拦截
 *
 * @author shaoming
 * @date 2021/8/30 14:20
 * @since 1.0
 */
public class FileDownloadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)){
            return false;
        }
//        String check = request.getParameter("check");
//        if (StringUtils.isBlank(check)){
//            return false;
//        }
//        code = code.replaceAll(" ", "+");
//        String checkCode = AesUtils.getCheckCodeByEncryptStr(code);
//        if (check.equals(checkCode)){
//            return true;
//        }
        return true;
    }
}
