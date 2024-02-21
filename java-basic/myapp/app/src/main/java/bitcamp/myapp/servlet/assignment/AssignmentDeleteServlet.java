package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/delete")
public class AssignmentDeleteServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
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
    printWriter.println("<h1>과제</h1>");

//    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
//    if (loginUser == null) {
//      printWriter.println("로그인 하세요");
//      printWriter.println("</body>");
//      printWriter.println("</html>");
//      return;
//    }

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Assignment assignment = assignmentDao.findBy(key);
    if (assignment == null) {
      printWriter.println("<p>게시글 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }
//    else if (board.getWriter().getNo() != loginUser.getNo()) {
//      printWriter.println("<p>접근 권한이 없습니다</p>");
//      printWriter.println("</body>");
//      printWriter.println("</html>");
//      return;
//    }

    assignmentDao.delete(key);

    printWriter.println("<script>");
    printWriter.println("  location.href = '/assignment/list'");
    printWriter.println("</script>");
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
