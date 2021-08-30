package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 创建分享链接后的封装类
 *
 * @author shaoming
 * @date 2021/8/30 17:06
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareFileLinkVo {
    private String link;
    private String check;
    private String until;
}
