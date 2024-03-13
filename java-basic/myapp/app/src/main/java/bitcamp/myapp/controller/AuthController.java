package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  MemberDao memberDao;

  public AuthController(MemberDao memberDao) {
    System.out.println("member Controller");
    this.memberDao = memberDao;
  }

  @RequestMapping("/auth/form")
  public String form(@CookieValue(value = "email", required = false) String email,
      Map<String, Object> map) {
    map.put("email", email);
    return "/auth/form.jsp";
  }


  @RequestMapping("/auth/login")
  public String login(
      @RequestParam(value = "email", required = false) String email,
      @RequestParam("password") String password,
      @RequestParam("saveEmail") String saveEmail, HttpSession session,
      HttpServletResponse response) throws Exception {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(86400);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member member = memberDao.findByEmailAndPassword(email, password);

    if (member != null) {
      session.setAttribute("loginUser", member);
    }

    return "/auth/login.jsp";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/index.html";
  }
}
