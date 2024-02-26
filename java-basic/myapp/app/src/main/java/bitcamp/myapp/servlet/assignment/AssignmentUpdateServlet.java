package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/update")
public class AssignmentUpdateServlet extends HttpServlet {

  private AssignmentDao assignmentDao;
  private TransactionManager txManager;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    try {

      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Assignment assignment = assignmentDao.findBy(key);
      if (assignment == null) {
        throw new Exception("과제 번호 오류");
      }

      assignment.setTitle(servletRequest.getParameter("title"));
      assignment.setContent(servletRequest.getParameter("content"));
      assignment.setDeadline(Date.valueOf(servletRequest.getParameter("deadline")));

      assignmentDao.update(assignment);
      servletResponse.sendRedirect("list");
    } catch (Exception e) {
      servletRequest.setAttribute("message", "변경 오류");
      servletRequest.setAttribute("error", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }


  }
}
