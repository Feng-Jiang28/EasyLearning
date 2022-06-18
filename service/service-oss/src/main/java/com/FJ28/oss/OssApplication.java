package com.FJ28.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


// 怎么使用OSS

// 1, 先使用一个类去读取配置文件

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 不使用db server
@ComponentScan({"com.FJ28"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}