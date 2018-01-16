package com.imooc.service.impl;

import com.imooc.entity.Users;
import com.imooc.mapper.UsersMapper;
import com.imooc.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author steve
 * @since 2018-01-03
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
	
}
