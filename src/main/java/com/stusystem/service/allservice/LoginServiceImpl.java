package com.stusystem.service.allservice;

import com.stusystem.dao.UserDao;
import com.stusystem.dao.UserDaoImpl;
import com.stusystem.domain.Admin;

public class LoginServiceImpl implements LoginService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public Admin login(Admin admin) {
        //返回查询到的admin
        //调用UserDao中方法查询管理员
        return dao.findAdmin(admin.getUuid(),admin.getPasswd());
    }
}
