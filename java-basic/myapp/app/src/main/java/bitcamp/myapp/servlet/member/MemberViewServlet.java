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

@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {

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
    printWriter.println("<h1>회원</h1>");

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Member member = memberDao.findBy(key);
    if (member == null) {
      printWriter.println("<p>회원 번호 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

//    List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);

    printWriter.println("<form action='/member/update' method='post'>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  번호: <input readonly name='no' type='text' value='%d'>\n",
        member.getNo());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  이름: <input name='name' type='text' value='%s' required>\n",
        member.getName());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf(" 이메일: <input name='email' type='email', value='%s' required>\n",
        member.getEmail());
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.printf(" 암호: <input name='password' type = 'password' value = '' required>\n");
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.printf("가입일: <input readonly type = 'text' value = '%s'>", member.getCreatedDate());
    printWriter.println("</div>");
//    printWriter.println("<ul>");
//    for (AttachedFile file : list) {
//      printWriter.printf(" <li>%s<a href = '/board/file/delete?no=%d'>삭제</a></li>\n",
//          file.getFilePath(), file.getNo());
//    }
//    printWriter.println("</ul>");
    printWriter.println("<div>");
    printWriter.println("  <button>변경</button>");
    printWriter.printf("<a href = '/member/delete?no=%d'>[삭제]</a>\n", key);
    printWriter.println("</div>");
    printWriter.println("</form>");

    printWriter.println("</body>");
    printWriter.println("</html>");

  }
}
