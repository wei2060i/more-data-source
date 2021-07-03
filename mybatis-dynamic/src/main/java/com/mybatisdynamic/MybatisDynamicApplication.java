package com.mybatisdynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Wei
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MybatisDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDynamicApplication.class, args);
    }

}
