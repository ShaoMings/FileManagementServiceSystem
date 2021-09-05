package com.graduation.project;

import cn.hutool.json.JSONUtil;
import com.graduation.mapper.FileMapper;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UploadResultVo;
import com.graduation.service.FileService;
import com.graduation.service.UserService;
import com.graduation.utils.Constant;
import com.graduation.utils.NetUtils;
import com.graduation.utils.PictureConverter;
import com.graduation.utils.download.SiteFileFetch;
import com.graduation.utils.download.SiteInfoBean;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class FileManagementServiceSystemApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    FileMapper fileMapper;

    @Test
    void contextLoads() {
        List<FileInfoVo> list = fileService.getFileInfoListByFileKeyword("http://10.211.55.102:8080/group1", "彼岸");
        list.forEach(System.out::println);
    }

    @Test
    void testStream() {

    }

    @Test
    void testUpdatePath() {

    }
}
