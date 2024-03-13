package com.eomcs.web.ex02;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex02/s01")
public class Servlet01 extends HttpServlet {

//  @Override
//  public void service(ServletRequest req, ServletResponse res)
//      throws ServletException, IOException {
//    System.out.println("/ex02/s01 서블릿 실행!");
//    res.getWriter().println("ex02/s01");  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().println("ex02/s01");
  }
}
