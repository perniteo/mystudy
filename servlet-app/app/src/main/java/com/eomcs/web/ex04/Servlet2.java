package com.eomcs.web.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex04/s2")
public class Servlet2 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String name = req.getParameter("name");
    String age = req.getParameter("age");


    resp.setContentType("text/plain;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();

    printWriter.printf("이름 : %s\n", name);
    printWriter.printf("나이 : %s\n", age);

    char[] chars = name.toCharArray();

    for (char ch : chars) {
      printWriter.printf("%x\n", (int) ch);
    }

  }

}
