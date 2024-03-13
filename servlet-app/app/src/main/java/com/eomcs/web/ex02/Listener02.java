package com.eomcs.web.ex02;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebListener
public class Listener02 implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    ServletRequestListener.super.requestDestroyed(sre);
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
    System.out.println(req.getRemoteAddr());
    System.out.println(req.getServletPath());
    int i = 30;
    System.out.println(Integer.toBinaryString(i).replace("0", "").length());
    System.out.println(Integer.bitCount(i));
  }
}
