package com.stusystem.dao;

import com.stusystem.domain.Admin;
import com.stusystem.domain.Student;

public interface UserDao {
    public Admin findAdmin(String admin_name , String password);
    public void add(Student stu);
    public void delStu(String id);
    public void ModifyStu(Student stu);
    public Student findByName(String name);
    public Student findByNum(String num);
}
