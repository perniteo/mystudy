package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/add")
public class AssignmentAddServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    req.setCharacterEncoding("UTF-8");

    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();

    printWriter.println("  <!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println(" <meta charset='UTF-8'>");
    printWriter.println(" <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    req.getRequestDispatcher("/header").include(req, resp);
    printWriter.println("<h1>과제 관리 시스템</h1>");
    printWriter.println("<h2>과제</h2>");
    printWriter.println("<form action='/assignment/add' method='post'>");
    printWriter.println(" <div>");
    printWriter.println("  <label>");
    printWriter.println("      제목: <input name='title' required type='text'>");
    printWriter.println("  </label>");
    printWriter.println("  </div>");
    printWriter.println("  <div>");
    printWriter.println("   <label>");
    printWriter.println("      내용: <textarea name='content' required></textarea>");
    printWriter.println("   </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println("   <label>");
    printWriter.println("     마감기한: <input name='deadline' required type='date'>");
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println(" <button>등록</button>");
    printWriter.println(" </div>");
    printWriter.println("</form>");

    req.getRequestDispatcher("/footer").include(req, resp);
    printWriter.println("</body>");
    printWriter.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      servletResponse.sendRedirect("/auth/login");
      return;
    }

    try {
      Assignment assignment = new Assignment();
      assignment.setTitle(servletRequest.getParameter("title"));
      assignment.setContent(servletRequest.getParameter("content"));
      assignment.setDeadline(Date.valueOf(servletRequest.getParameter("deadline")));

      assignmentDao.add(assignment);

      servletResponse.sendRedirect("/assignment/list");
    } catch (Exception e) {
      servletRequest.setAttribute("message", "등록 오류");
      servletRequest.setAttribute("error", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }

  }
}

