package bitcamp.myapp.controller;

import bitcamp.util.Component;

@Component
public class HomeController {

  @RequestMapping("/home")
  public String execute() throws Exception {
    return "/home.jsp";
  }
}
