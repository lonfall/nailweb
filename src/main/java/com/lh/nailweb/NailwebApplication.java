package com.lh.nailweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.lh.nailweb"})
public class NailwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NailwebApplication.class, args);
    }

}
