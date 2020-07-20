package com.stusystem.service.allservice;

import com.stusystem.domain.Admin;

/**
 * 管理员登录的业务接口
 * */
public interface LoginService {
    //返回查询到的admin
        public Admin login(Admin admin);
}
