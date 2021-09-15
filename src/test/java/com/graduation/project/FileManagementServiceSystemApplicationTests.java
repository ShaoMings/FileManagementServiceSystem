package com.graduation.project;

import com.graduation.mapper.FileMapper;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.service.FileService;
import com.graduation.service.UserRoleService;
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
    UserRoleService userRoleService;

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
        System.out.println(userRoleService.updateUserRoleByUserId(7, 3));
    }
}
