package com.mybatisdynamic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mybatisdynamic.consts.DataSource;
import com.mybatisdynamic.model.po.User;
import com.mybatisdynamic.dao.UserDao;
import com.mybatisdynamic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wei
 * @since 2021-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Override
    public void testMaster() {
        List<User> list = list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Override
    @DS(DataSource.WRITE)
    public void testWrite() {
        List<User> list = list();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
