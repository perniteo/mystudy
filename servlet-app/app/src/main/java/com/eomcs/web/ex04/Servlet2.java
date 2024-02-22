package com.eomcs.web.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/web/ex04/s1")
public class Servlet1 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String name = req.getParameter("name");
    String age = req.getParameter("age");

    resp.setContentType("text/plain;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();
    printWriter.printf("이름 : %s\n", name);
    printWriter.printf("나이 : %s\n", age);

  }

}
