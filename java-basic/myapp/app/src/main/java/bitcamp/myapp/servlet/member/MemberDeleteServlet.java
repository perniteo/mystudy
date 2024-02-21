package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    System.out.println("service() 호출");

    servletResponse.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = servletResponse.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println("  <meta charset='UTF-8'>");
    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    printWriter.println("<h1>회원</h1>");

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("로그인 하세요");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Member member = memberDao.findBy(key);
    if (member == null) {
      printWriter.println("<p>회원 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    } else if (member.getNo() != loginUser.getNo()) {
      printWriter.println("<p>접근 권한이 없습니다</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    memberDao.delete(key);

    printWriter.println("<script>");
    printWriter.println("  location.href = '/member/list'");
    printWriter.println("</script>");
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
