package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }


  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
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

    servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);

    printWriter.println("<h1>회원</h1>");

    printWriter.println("<a href ='/member/add'>회원 가입</a>");

    try {
      printWriter.println("<table border='1'>");
      printWriter.println("    <thead>");
      printWriter.println(
          "    <tr> <th>번호</th> <th>이름</th> <th>이메일</th> <th>가입일</th> </tr>");
      printWriter.println("    </thead>");
      printWriter.println("    <tbody>");

      List<Member> list = memberDao.findAll();

      for (Member member : list) {
        printWriter.printf(
            "<tr> <td>%d</td> <td><img src='/upload/%s' height='25px'><a href = '/member/view?no=%1$d'>%s</td> <td>%s</td> <td>%s</td> </tr>\n",
            member.getNo(),
            member.getPhoto() == null ? "img/default-photo.jpeg" : member.getPhoto(),
            member.getName(),
            member.getEmail(),
            member.getCreatedDate());
      }

      printWriter.println("    </tbody>");
      printWriter.println("</table>");

    } catch (Exception e) {
      servletRequest.setAttribute("message", "목록 오류!");
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }

    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);

    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
