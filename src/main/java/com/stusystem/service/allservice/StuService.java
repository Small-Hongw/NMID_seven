package com.stusystem.service.allservice;

import com.stusystem.domain.Student;

public interface StuService {
    public void addStu(Student stu);
    public void delStu(String id);
    public void modifyStu(Student stu);
    public Student findByName(String name);
    public Student findByNum(String num);
}
