package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/list")
public class AssignmentListServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
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
    printWriter.println("<h1>과제</h1>");

    printWriter.println("<a href ='/assignment/add'>등록</a>");

    try {
      printWriter.println("<table border='1'>");
      printWriter.println("    <thead>");
      printWriter.println(
          "    <tr> <th>번호</th> <th>제목</th> <th>내용</th> <th>마감 기한</th> </tr>");
      printWriter.println("    </thead>");
      printWriter.println("    <tbody>");

      List<Assignment> list = assignmentDao.findAll();

      for (Assignment assignment : list) {
        printWriter.printf(
            "<tr> <td>%d</td> <td><a href = '/assignment/view?no=%1$d'>%s</td> <td>%s</td> <td>%s</td> </tr>\n",
            assignment.getNo(),
            assignment.getTitle(),
            assignment.getContent(),
            assignment.getDeadline());
      }

      printWriter.println("    </tbody>");
      printWriter.println("</table>");

    } catch (Exception e) {
      servletRequest.setAttribute("message", "목록 오류");
      servletRequest.setAttribute("error", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }

    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
