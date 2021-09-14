package com.graduation.service;

import com.graduation.model.dto.PageInfoDto;
import com.graduation.model.pojo.Peers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
public interface PeersService extends IService<Peers> {

    /**
     *  获取集群列表 通过分页处理
     * @param page 当前页码
     * @param limit 页面记录条数
     * @return 集群列表
     */
    PageInfoDto<Peers> listPage(int page, int limit);

    /**
     * 获取所有保存的集群对象
     * @return 所有集群
     */
    List<Peers> getAllPeers();

    /**
     * 检查集群地址是否可用
     * @param serverAddress 集群地址 启用组名则加上组名
     * @return 是否可用
     */
    boolean checkPeersAddressIsUseful(String serverAddress);

    /**
     * 获取集群状态信息
     * @param serverAddress 集群地址 启用组名则加上组名
     * @return 集群状态集合
     */
    Map<String,Object> getPeersStatus(String serverAddress);


    /**
     *  检查集群是否存在
     * @param serverAddress 服务地址
     * @return 是否存在
     */
    boolean checkPeersExist(String serverAddress);

    /**
     *  添加集群
     * @param peers 集群对象
     * @return 是否添加成功
     */
    boolean addPeers(Peers peers);

    /**
     * 通过id删除集群
     * @param id 集群id
     * @return 是否删除成功
     */
    boolean deletePeersById(int id);

}
