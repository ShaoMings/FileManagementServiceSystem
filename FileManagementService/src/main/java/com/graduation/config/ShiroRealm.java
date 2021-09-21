package com.graduation.config;

import com.graduation.model.pojo.User;
import com.graduation.model.vo.UserLoginVo;
import com.graduation.service.PeersService;
import com.graduation.service.UserService;
import com.graduation.utils.RegexUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Description 用户身份认证与授权
 *
 * @author shaoming
 * @date 2021/8/17 13:00
 * @since 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    PeersService peersService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名称 (前端控制器入参)
        UserLoginVo user = new UserLoginVo();
        try {
            BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String account = userToken.getUsername();
        User user = null;
        if (RegexUtils.verifyEmail(account)) {
                user = userService.getUserByEmail(account);
        }else {
                user = userService.getUserByUserName(account);
        }
        if (user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getPassword() == null){
            throw new IncorrectCredentialsException("密码为空");
        }else {
            String md5Hash = new Md5Hash(userToken.getPassword(),user.getCredentialsSalt()).toString();
            userToken.setPassword(md5Hash.toCharArray());
        }
        String password = user.getPassword();
        user.setPassword(null);
        // 获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 将集群对象存入session
        request.getSession().setAttribute("peers",peersService.getById(user.getPeersid()));
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
