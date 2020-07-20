package com.stusystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stusystem.domain.JsonData;
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

@WebServlet(name = "ModifyStuServlet",urlPatterns = "/ModifyStuServlet")
public class ModifyStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
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
        //4.调用service进行修改
        StuService service =new StuServiceImpl();
        service.modifyStu(stu);
        //5.返回json
        JsonData js =new JsonData();
        js.setStatus(200);
        js.setMsg("请求成功");
        js.setData(stuMap);
        response.setHeader("Content-Type","application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(js));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
