package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/view")
public class AssignmentViewServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

//    System.out.println("service() 호출");
    try {

      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Assignment assignment = assignmentDao.findBy(key);
      if (assignment == null) {
        throw new Exception("과제 번호 오류");
      }

      servletRequest.setAttribute("assignment", assignment);

      servletRequest.getRequestDispatcher("/assignment/view.jsp")
          .forward(servletRequest, servletResponse);

//      servletResponse.setContentType("text/html;charset=UTF-8");
//      PrintWriter printWriter = servletResponse.getWriter();
//
//      printWriter.println("<!DOCTYPE html>");
//      printWriter.println("<html lang='en'>");
//      printWriter.println("<head>");
//      printWriter.println("  <meta charset='UTF-8'>");
//      printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
//      printWriter.println("</head>");
//      printWriter.println("<body>");
//      servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);
//      printWriter.println("<h1>과제</h1>");
//
//      printWriter.println("<form action='/assignment/update' method='post'>");
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf("  번호: <input readonly name='no' type='text' value='%d'>\n",
//          assignment.getNo());
//      printWriter.println("  </label>");
//      printWriter.println("</div>");
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf("  제목: <input name='title' type='text' value='%s'>\n",
//          assignment.getTitle());
//      printWriter.println("  </label>");
//      printWriter.println("</div>");
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf(" 내용: <textarea name = 'content'>%s</textarea>\n",
//          assignment.getContent());
//      printWriter.println(" </label>");
//      printWriter.println(" </div>");
//      printWriter.println("<div>");
//      printWriter.printf(" 마감기한: <input name = 'deadline' type = 'date' value = '%s'>\n",
//          assignment.getDeadline());
//      printWriter.println(" </div>");
//      printWriter.println("<div>");
//      printWriter.println("  <button>변경</button>");
//      printWriter.printf("<a href = '/assignment/delete?no=%d'>[삭제]</a>\n", key);
//      printWriter.println("</div>");
//      printWriter.println("</form>");
//
//      servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
//      printWriter.println("</body>");
//      printWriter.println("</html>");

    } catch (Exception e) {
      servletRequest.setAttribute("message", "번호 오류");
      servletRequest.setAttribute("error", e);
      servletRequest.getRequestDispatcher("/error.jsp").forward(servletRequest, servletResponse);
    }


  }
}
