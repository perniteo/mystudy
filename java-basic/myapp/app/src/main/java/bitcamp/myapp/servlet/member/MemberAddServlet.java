package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding("UTF-8");

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = response.getWriter();

    printWriter.println(" <!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println(" <meta charset='UTF-8'>");
    printWriter.println("<title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    printWriter.println("<h1>과제 관리 시스템</h1>");

    printWriter.println("<h2>회원</h2>");

    printWriter.println("<form action='member/add' method='post'>");
    printWriter.println("  <div>");
    printWriter.println("  <label>");
    printWriter.println("      이름: <input name='name' type='text'>");
    printWriter.println("  </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println("  <label>");
    printWriter.println("      이메일: <input name='email' type='text'>");
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println("  <label>");
    printWriter.println("   암호:");
    printWriter.println("  <input name='password' type='password'>");
    printWriter.println(" </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<button>가입</button>");
    printWriter.println("</div>");
    printWriter.println("</form>");

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {
    servletRequest.setCharacterEncoding("UTF-8");

    servletResponse.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = servletResponse.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println("  <meta charset='UTF-8'>");
    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);
    printWriter.println("<h1>회원</h1>");

    Member member = new Member();
    member.setName(servletRequest.getParameter("name"));
    member.setEmail(servletRequest.getParameter("email"));
    member.setPassword(servletRequest.getParameter("password"));

    try {
      txManager.startTransaction();

      memberDao.add(member);

      txManager.commit();

      printWriter.println("<p>회원 가입 완료</p>");

      servletResponse.setHeader("refresh", "1;url=index/html");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        printWriter.println("<p>회원 가입 실패</p>");
      }
    }
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}

