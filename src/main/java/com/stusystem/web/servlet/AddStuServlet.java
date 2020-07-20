package com.stusystem.web.servlet;

import com.stusystem.domain.Student;
import com.stusystem.service.allservice.StuService;
import com.stusystem.service.allservice.StuServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "AddStuServlet",urlPatterns = "/AddStuServlet")
public class AddStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        Map<String, String[]> stuMap = request.getParameterMap();
        //3.封装对象
        Student stu = new Student();
        try {
            BeanUtils.populate(stu,stuMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用StuService进行添加
        StuService service =new StuServiceImpl();
        service.addStu(stu);
        response.setStatus(201);
        response.setHeader("Content-Type","application/json");
        response.getWriter().write("添加成功");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
