package com.stusystem.dao;

import com.stusystem.domain.Admin;
import com.stusystem.domain.Student;
import com.stusystem.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Override
    public Admin findAdmin(String admin_name, String password) {
        //创建一个Admin对象用来接收查询到的管理员
        Admin ad =null;
        //获取连接，statement
        try {
            conn = DBUtil.getCon();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //定义sql语句，进行查询
        String sql = "select * from admin_table where uuid="+admin_name+" and passwd="+password;
        try {
            rs = stmt.executeQuery(sql);
            if(rs !=null){
                ad=new Admin();
                ad.setUuid(rs.getString("admin_name"));
                ad.setPasswd(rs.getString("admin_pw"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(rs,stmt,conn);
        return ad;
    }

    @Override
    public void add(Student stu) {
        //定义sql语句，进行添加
        String sql ="insert into stu_table values(null,"+stu.getName()+","+
                stu.getGender()+","+stu.getAge()+","+stu.getAge()+","+stu.getMajor()+")";
        try {
            conn = DBUtil.getCon();
            stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(stmt,conn);
    }

    @Override
    public void delStu(String id) {
        //1.定义sql语句，进行删除
        String sql ="delete from stu_table where stuNumber="+id;
        try {
            conn = DBUtil.getCon();
            stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(stmt,conn);
    }

    @Override
    public void ModifyStu(Student stu) {
        //定义sql语句进行修改
        String sql = "update stu_table where stuNumber="+stu.getStuNumber()+" set "
                +"name="+stu.getName()+" gender="+stu.getGender()+" age="+stu.getAge()+" major="+stu.getMajor();
        try {
            conn = DBUtil.getCon();
            stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(stmt,conn);
    }

    @Override
    public Student findByName(String name) {
        Student stu =new Student();
        try {
            conn = DBUtil.getCon();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //定义sql语句，进行查询
        String sql = "select * from stu_table where name="+name;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stu;
    }

    @Override
    public Student findByNum(String num) {
        Student stu =new Student();
        try {
            conn = DBUtil.getCon();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //定义sql语句，进行查询
        String sql = "select * from stu_table where name="+num;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
