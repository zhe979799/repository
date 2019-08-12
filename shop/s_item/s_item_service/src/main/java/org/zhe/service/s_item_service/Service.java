package org.zhe.service.s_item_service;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("org.zhe.service.s_item_service.item.mapper")
public class Service {
    public static void main(String[] args) {
        SpringApplication.run(Service.class,args);
    }


}
