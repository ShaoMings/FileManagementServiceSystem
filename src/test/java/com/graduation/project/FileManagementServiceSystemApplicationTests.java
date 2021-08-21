package com.graduation.project;

import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.service.FileService;
import com.graduation.service.UserService;
import com.graduation.utils.PictureConverter;
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

    @Test
    void contextLoads() {
        List<FileInfoVo> list = fileService.getFileInfoListByFileKeyword("http://10.211.55.102:8080/group1", "彼岸");
        list.forEach(System.out::println);
    }

    @Test
    void testStream() throws FileNotFoundException {

    }

}
