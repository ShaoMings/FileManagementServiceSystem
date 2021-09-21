package com.graduation;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaoming
 */
@Slf4j
@MapperScan(basePackages = {"com.graduation.mapper"})
@SpringBootApplication
public class FileManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManagementServiceApplication.class, args);
    }

}
