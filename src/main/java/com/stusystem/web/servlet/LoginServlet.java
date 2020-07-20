package com.stusystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stusystem.domain.Admin;
import com.stusystem.domain.JsonData;
import com.stusystem.service.allservice.LoginService;
import com.stusystem.service.allservice.LoginServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        //2.1获取管理员登录用户名与密码
        Map<String, String[]> adminMap = request.getParameterMap();

        //3.封装Admin对象
        Admin admin =new Admin();
        try {
            BeanUtils.populate(admin, adminMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用LoginService查询
        LoginService service = new LoginServiceImpl();
        //adminIn装入查询到的管理员
        Admin adminIn = service.login(admin);
        //获取session
        HttpSession session = request.getSession();
        //5.判断登录是否成功
        if(adminIn !=null){
            //登录成功
            //将用户存入session
            session.setAttribute("admin",adminIn);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            //创建Json类，返回json数据
            JsonData js =new JsonData();
            js.setStatus(200);
            js.setMsg("请求成功");
            js.setData(adminMap);
            response.getWriter().write(new ObjectMapper().writeValueAsString(js));

        }else {
            //登录失败
            response.setStatus(401);
            JsonData js =new JsonData();
            js.setStatus(401);
            js.setMsg("请求失败");
            response.getWriter().write(new ObjectMapper().writeValueAsString(js));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
