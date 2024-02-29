package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

//    String email = "";
    Cookie[] cookies = req.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("email")) {
        req.setAttribute("email", cookie.getValue());
//        email = cookie.getValue();
        break;
      }
    }

    req.setAttribute("viewUrl", "/auth/form.jsp");
//    req.getRequestDispatcher("/auth/form.jsp").forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {

      String email = req.getParameter("email");
      String password = req.getParameter("password");
      String saveEmail = req.getParameter("saveEmail");

      ArrayList<Cookie> cookies = new ArrayList<>();

      if (saveEmail != null) {
        Cookie cookie = new Cookie("email", email);
        int DAY_SECOND = 86400;
        cookie.setMaxAge(DAY_SECOND);
        cookies.add(cookie);
//        resp.addCookie(cookie);
      } else {
        Cookie cookie = new Cookie("email", "");
        cookies.add(cookie);
//        resp.addCookie(cookie);
      }

      Member member = memberDao.findByEmailAndPassword(email, password);
      if (member != null) {
        req.getSession().setAttribute("loginUser", member);

      }

      req.setAttribute("viewUrl", "/auth/login.jsp");

//      req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    } catch (Exception e) {
      req.setAttribute("exception", e);
//      req.setAttribute("message", "로그인 오류!");
//      req.setAttribute("exception", e);
//      req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
  }

}
