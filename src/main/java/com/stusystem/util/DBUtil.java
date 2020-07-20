package com.stusystem.util;

import com.sun.deploy.association.utility.AppConstants;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        //获取连接所需要的值
        try {
            //创建Properties
            Properties pro =new Properties();
            //加载文件资源
            pro.load(new FileReader("E:\\javaProject\\studentSystem\\src\\jdbc.properties"));
            //获取值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     * @return返回连接对象
     */
    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt,Connection conn)  {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放资源
     * @param stmt
     * @param conn
     * @param rs
     */
    public static void close(ResultSet rs,Statement stmt,Connection conn)  {
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
