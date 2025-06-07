package com.example.bookmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bookmanage.mapper")
public class BookManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManageApplication.class, args);
    }
}