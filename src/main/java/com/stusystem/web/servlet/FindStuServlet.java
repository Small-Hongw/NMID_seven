package com.stusystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stusystem.domain.JsonData;
import com.stusystem.domain.Student;
import com.stusystem.service.allservice.StuService;
import com.stusystem.service.allservice.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindStuServlet",urlPatterns = "/FindStuServlet")
public class FindStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取信息
        String fd1 =request.getParameter("name");
        String fd2 =request.getParameter("stuNumber");
        //3.创建Student接收返回对象
        Student stu =new Student();
        //4.调用service进行查询
        StuService service= new StuServiceImpl();
        if(fd1 !=null){
            stu =service.findByName(fd1);
        }else stu =service.findByNum(fd2);
        //5.创建json对象，进行返回json数据
        JsonData js =new JsonData();
        js.setStatus(200);
        js.setMsg("查询成功");
        js.setStu(stu);
        response.setStatus(200);
        response.setHeader("Content-Type","application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(js));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
