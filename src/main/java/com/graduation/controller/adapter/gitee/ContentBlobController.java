package com.graduation.controller.adapter.gitee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.graduation.model.dto.gitee.request.ContentBlobDto;
import com.graduation.model.dto.gitee.response.SingleFileResultDto;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description 仓库内容  文件内容控制器
 *
 * @author shaoming
 * @date 2021/10/16 15:02
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class ContentBlobController {

    @RequestMapping("/blob")
    public void getBlobContent(ContentBlobDto dto, HttpServletResponse response) throws IOException {
        boolean hasToken = true;
        if (StringUtils.isBlank(dto.getAccess_token())){
            hasToken = false;
        }
            String request = "https://gitee.com/api/v5/repos/"+dto.getOwner()+"/"
                    + dto.getRepo()+"/git/blobs/"+dto.getSha()+ (hasToken?"?access_token="+dto.getAccess_token():"");
        String json = HttpUtil.get(request);
        SingleFileResultDto resultDto = JSONUtil.toBean(json, SingleFileResultDto.class);
        String content = resultDto.getContent();
        if (StringUtils.isNotBlank(content)){
            byte[] bytes = Base64.decodeBase64(content);
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=" + dto.getFilename());
            out.write(bytes);
            out.flush();
        }
    }
}
