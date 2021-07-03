package com.mybatisdynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Wei
 */
@MapperScan("com.mybatisdynamic.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MybatisDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDynamicApplication.class, args);
    }

}
