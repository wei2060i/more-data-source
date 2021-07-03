package com.mybatisdynamic;

import com.mybatisdynamic.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MybatisDynamicApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    void contextLoads() {
        userService.testMaster();
    }



}
