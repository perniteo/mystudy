package bitcamp.myapp.controller;

import org.springframework.stereotype.Component;

@Component
public class AboutController {

  public AboutController() {
    System.out.println("about Controller");
  }

  @RequestMapping("/about")
  public String execute() throws Exception {
    return "/about.jsp";
  }
}
