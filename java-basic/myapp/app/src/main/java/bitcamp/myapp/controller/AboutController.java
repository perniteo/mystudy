package bitcamp.myapp.controller;

import bitcamp.util.Component;

@Component
public class AboutController {

  @RequestMapping("/about")
  public String execute() throws Exception {
    return "/about.jsp";
  }
}
