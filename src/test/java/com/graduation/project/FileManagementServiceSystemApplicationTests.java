package com.graduation.project;

import com.graduation.model.pojo.User;
import com.graduation.service.FileService;
import com.graduation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FileManagementServiceSystemApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Test
    void contextLoads() {
        System.out.println(fileService.saveFilePathByUserId(3, "/test/page"));
    }

}
