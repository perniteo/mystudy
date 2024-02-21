package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
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

  private TransactionManager txManager;
  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
  }

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

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

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("로그인 후 사용 가능");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    Assignment assignment = new Assignment();
    assignment.setTitle(servletRequest.getParameter("title"));
    assignment.setContent(servletRequest.getParameter("content"));
    assignment.setDeadline(Date.valueOf(servletRequest.getParameter("deadline")));
//    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

//    String[] files = servletRequest.getParameterValues("files");
//    if (files != null) {
//      for (String file : files) {
//        if (file.isEmpty()) {
//          continue;
//        }
//        attachedFiles.add(new AttachedFile().filePath(file));
//      }
    try {
      txManager.startTransaction();

      assignmentDao.add(assignment);

//        if (!attachedFiles.isEmpty()) {
//          // 첨부파일 객체에 게시글 번호 저장
//          for (AttachedFile file : attachedFiles) {
//            file.setBoardNo(board.getNo());
//          }
//          attachedFileDao.addAll(attachedFiles);
//        }
      txManager.commit();

      printWriter.println("<p>과제 등록 완료</p>");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        printWriter.println("<p>과제 등록 실패</p>");
      }
    }
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}

