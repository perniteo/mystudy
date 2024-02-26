package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println(" <meta charset='UTF-8'>");
    printWriter.println(" <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("<body>");

    req.getRequestDispatcher("/header").include(req, resp);

    printWriter.println("<h1>과제 관리 시스템</h1>");
    printWriter.println("<h2>로그인</h2>");
    printWriter.println("<form action='/auth/login' method='post'>");
    printWriter.println(" <div>");
    printWriter.println("  이메일: <input name='email' type='text'>");
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.println(" 암호: <input name='password' type='password'>");
    printWriter.println(" </div>");
    printWriter.println("<button>로그인</button>");
    printWriter.println("</form>");

    req.getRequestDispatcher("/footer").include(req, resp);

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    String email = req.getParameter("email");
    String password = req.getParameter("password");

    PrintWriter printWriter = resp.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println("  <meta charset='UTF-8'>");
    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");

    req.getRequestDispatcher("/header").include(req, resp);

    printWriter.println("<h1>과제 관리 시스템</h1>");
    printWriter.println("<h2>로그인</h2>");

    try {
      Member member = memberDao.findByEmailAndPassword(email, password);
      if (member != null) {
        req.getSession().setAttribute("loginUser", member);
        printWriter.printf("<p>%s 님 환영합니다.</p>\n", member.getName());
        resp.setHeader("refresh", "1;url=/index.html");

      } else {
        printWriter.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
        resp.setHeader("refresh", "1;url=/auth/login");
      }
    } catch (Exception e) {
      printWriter.println("<p>목록 오류!</p>");
      printWriter.println("<pre>");
      e.printStackTrace(printWriter);
      printWriter.println("</pre>");
    }
    req.getRequestDispatcher("/footer").include(req, resp);

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

}
