package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
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
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    try {

      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Assignment assignment = assignmentDao.findBy(key);
      if (assignment == null) {
        throw new Exception("과제 번호 오류");
      }
      assignmentDao.delete(key);
      servletResponse.sendRedirect("list");

    } catch (Exception e) {
      servletRequest.setAttribute("message", "삭제 오류");
      servletRequest.setAttribute("error", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }


  }
}
