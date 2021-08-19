package com.graduation.model.vo;

import cn.hutool.core.util.IdUtil;
import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import lombok.Data;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Description 登录初始化参数对象
 *
 * @author shaoming
 * @date 2021/8/17 14:43
 * @since 1.0
 */
@Data
public class InstallVo implements Serializable {
    private static final long serialVersionUID = -6474666305055871893L;
    @NotBlank(message = "集群名称不能为空且在50字以内")
    @Size(max = 50, message = "集群名称不能为空且在50字以内")
    private String name;
    @Size(max =50, message = "组名称应在50字以内")
    private String groupName;
    @NotBlank(message = "集群服务地址不能为空且在100字以内")
    @Size(max = 100, message = "集群服务地址不能为空且在100字以内")
    private String serverAddress;

    @Size(max = 100, message = "访问域名应在50字以内")
    private String showAddress;

    @NotBlank(message = "账户不能为空且在30字以内")
    @Size(max = 30, message = "账户不能为空且在30字以内")
    private String account;
    @NotBlank(message = "密码不能为空且在30字以内")
    @Size(max = 30, message = "密码不能为空且在30字以内")
    private String password;
    @NotBlank(message = "用户名不能为空且在30字以内")
    @Size(max = 30, message = "用户名不能为空且在30字以内")
    private String userName;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "请检查邮箱格式是否正确")
    private String email;


    public User getUser(){
        User user = new User();
        // uuid作为盐
        String uuid = IdUtil.simpleUUID();
        // 密码加密
        Md5Hash md5Hash = new Md5Hash(this.password, uuid);
        user.setPassword(md5Hash.toString());
        // 保存盐值
        user.setCredentialsSalt(uuid);
        user.setUsername(this.account);
        user.setEmail(this.email);
        user.setRealName(this.name);
        // 插入时间
        user.setCreateTime(new Date());
        return user;
    }

    public Peers getPeers(){
        Peers peers = new Peers();
        peers.setGroupName(this.groupName);
        peers.setServerAddress(this.serverAddress);
        peers.setName(this.name);
        peers.setShowAddress(this.showAddress);
        // 插入时间
        peers.setCreateTime(new Date());
        return peers;
    }
}
