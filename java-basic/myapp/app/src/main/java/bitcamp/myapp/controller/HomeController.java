package bitcamp.myapp.controller;

public class HomeController {

  @RequestMapping("/home")
  public String execute() throws Exception {
    return "/home.jsp";
  }
}
