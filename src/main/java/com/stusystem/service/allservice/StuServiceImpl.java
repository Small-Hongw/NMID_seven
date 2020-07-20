package com.stusystem.service.allservice;

import com.stusystem.dao.UserDao;
import com.stusystem.dao.UserDaoImpl;
import com.stusystem.domain.Student;

public class StuServiceImpl implements StuService {
    UserDao dao = new UserDaoImpl();
    @Override
    public void addStu(Student stu) {
        dao.add(stu);
    }

    @Override
    public void delStu(String id) {
        dao.delStu(id);
    }

    @Override
    public void modifyStu(Student stu) {
        dao.ModifyStu(stu);
    }

    @Override
    public Student findByName(String name) {

        return dao.findByName(name);
    }

    @Override
    public Student findByNum(String num) {
        return dao.findByNum(num);
    }
}
