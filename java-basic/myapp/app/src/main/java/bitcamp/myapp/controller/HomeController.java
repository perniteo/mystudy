package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

  @RequestMapping("/home")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "/home.jsp";
  }
}
