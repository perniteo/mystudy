package bitcamp.myapp.controller;

import org.springframework.stereotype.Component;

@Component
public class HomeController {

  public HomeController() {
    System.out.println("home Controller");
  }

  @RequestMapping("/home")
  public String execute() throws Exception {
    return "/home.jsp";
  }
}
