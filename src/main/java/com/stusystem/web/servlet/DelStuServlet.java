package com.stusystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stusystem.domain.JsonData;
import com.stusystem.service.allservice.StuService;
import com.stusystem.service.allservice.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "DelStuServlet",urlPatterns = "/DelStuServlet")
public class DelStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取学号
        String id = request.getParameter("stuNumber");
        //3.调用service删除
        StuService service =new StuServiceImpl();
        service.delStu(id);
        //4.创建json，返回json数据
        JsonData js = new JsonData();
        js.setStatus(204);
        js.setMsg("删除成功");
        response.setHeader("Content-Type","application/json");
        response.setStatus(204);
        response.getWriter().write(new ObjectMapper().writeValueAsString(js));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
