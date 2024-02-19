package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/view")
public class AssignmentViewServlet extends HttpServlet {

  private final AssignmentDao assignmentDao;

  public AssignmentViewServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.assignmentDao = new AssignmentDaoImpl(dbConnectionPool);
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

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Assignment assignment = assignmentDao.findBy(key);
    if (assignment == null) {
      printWriter.println("<p>과제 번호 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

//    List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);

    printWriter.println("<form action='/assignment/update'>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  번호: <input readonly name='no' type='text' value='%d'>\n",
        assignment.getNo());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  제목: <input name='title' type='text' value='%s'>\n",
        assignment.getTitle());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf(" 내용: <textarea name = 'content'>%s</textarea>\n", assignment.getContent());
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.printf(" 마감기한: <input name = 'deadline' type = 'date' value = '%s'>\n",
        assignment.getDeadline());
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.println("  <button>변경</button>");
    printWriter.printf("<a href = '/assignment/delete?no=%d'>[삭제]</a>\n", key);
    printWriter.println("</div>");
    printWriter.println("</form>");

    printWriter.println("</body>");
    printWriter.println("</html>");

  }
}
