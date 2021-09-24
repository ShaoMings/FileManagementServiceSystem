package com.graduation.service;

import com.graduation.model.pojo.MailReceive;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
public interface MailReceiveService extends IService<MailReceive> {

    /**
     * 通过用户id 获取该用户的信箱记录
     * @param userId 用户id
     * @return 信箱记录
     */
    List<MailReceive> getMailReceivesByUserId(Integer userId);

    /**
     * 删除单条信件信息
     * @param mailId 信件id
     * @return 是否删除成功
     */
    boolean deleteMailReceiveByMailId(Integer mailId);

    /**
     *  通过用户选中的信件id删除该用户的信件记录
     * @param ids 信件id
     * @return 是否删除成功
     */
    boolean deleteMailReceivesByMailIds(Integer[] ids);

    /**
     * 通过 收件记录id修改已读状态
     * @param id 接收记录id
     * @return 是否修改成功
     */
    boolean changeReadStatusById(Integer id);

    /**
     *  是否有未读信息
     * @param userId  用户id
     * @return 是否有未读信息
     */
    boolean hasUnreadMessages(Integer userId);

    /**
     * 通过用户名通知用户公开分享记录被删除
     * @param username 被通知的用户名
     * @param admin 管理员名
     * @param fileName 文件名
     * @param shareTime 文件公开分享时间
     * @return 是否通知成功
     */
    boolean addNoticeOfShareDeletedByUserName(String username,String admin,String fileName,String shareTime);
}
