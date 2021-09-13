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
}
