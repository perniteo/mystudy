package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/login")
public class LoginServlet extends GenericServlet {

  MemberDao memberDao;

  public LoginServlet() {
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123"
    );
    this.memberDao = new MemberDaoImpl(connectionPool);
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    httpServletResponse.setContentType("text/html;charset=UTF-8");

    String email = httpServletRequest.getParameter("email");
    String password = httpServletRequest.getParameter("password");

    PrintWriter printWriter = httpServletResponse.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println("  <meta charset='UTF-8'>");
    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    printWriter.println("<h1>과제 관리 시스템</h1>");
    printWriter.println("<h2>로그인</h2>");

    try {
      Member member = memberDao.findByEmailAndPassword(email, password);
      if (member != null) {
        httpServletRequest.getSession().setAttribute("loginUser", member);
        printWriter.printf("<p>%s 님 환영합니다.</p>\n", member.getName());

      } else {
        printWriter.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
      }
    } catch (Exception e) {
      printWriter.println("<p>목록 오류!</p>");
      printWriter.println("<pre>");
      e.printStackTrace(printWriter);
      printWriter.println("</pre>");
    }

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

}
