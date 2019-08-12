package org.zhe.service.s_upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Upload {
    public static void main(String[] args) {
        SpringApplication.run(Upload.class,args);
    }
}
