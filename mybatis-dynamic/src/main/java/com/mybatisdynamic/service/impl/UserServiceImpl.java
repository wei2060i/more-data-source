package com.mybatisdynamic.service.impl;

import com.mybatisdynamic.model.po.User;
import com.mybatisdynamic.dao.UserDao;
import com.mybatisdynamic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
