package com.eomcs.web.ex03;

import com.google.protobuf.ByteString.Output;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/web/ex03/s1")
public class Servlet1 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    ServletContext ctx = req.getServletContext();

    String path = ctx.getRealPath("/elon.jpg");
    System.out.println(path);

    FileInputStream in = new FileInputStream(path);

    resp.setContentType("image/jpg");

    OutputStream out = resp.getOutputStream();

    int data;
    while ((data = in.read()) != -1) {
      out.write(data);
    }

    out.flush();
    out.close();
    in.close();
  }

}
