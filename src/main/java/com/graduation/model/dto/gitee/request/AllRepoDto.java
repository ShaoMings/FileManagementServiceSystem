package com.graduation.model.dto.gitee.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 获取授权用户的所有仓库 api请求参数
 *
 * @author shaoming
 * @date 2021/10/15 15:48
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllRepoDto {
    /** 用户授权码 */
    private String access_token;
    /** 公开(public)、私有(private)或者所有(all)，默认: 所有(all) */
    private String visibility;
    /** owner(授权用户拥有的仓库)、collaborator(授权用户为仓库成员)、organization_member(授权用户为仓库所在组织并有访问仓库权限)、
     * enterprise_member(授权用户所在企业并有访问仓库权限)、admin(所有有权限的，包括所管理的组织中所有仓库、
     * 所管理的企业的所有仓库)。 可以用逗号分隔符组合。
     * 如: owner, organization_member 或 owner, collaborator, organization_member*/
    private String affiliation;
    /** 排序方式: 创建时间(created)，更新时间(updated)，最后推送时间(pushed)，仓库所属与名称(full_name)。默认: full_name*/
    private String sort;
    /** 如果sort参数为full_name，用升序(asc)。否则降序(desc)*/
    private String direction;
    /** 当前的页码*/
    private Integer page;
    /** 每页的数量，最大为 100 */
    private Integer per_page;

}
