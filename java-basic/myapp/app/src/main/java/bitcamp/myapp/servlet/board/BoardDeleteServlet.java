package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private TransactionManager txManager;
  private String uploadDir;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    attachedFileDao = (AttachedFileDao) this.getServletContext().getAttribute("attachedFileDao");
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    String title = null;

    System.out.println("service() 호출");
    try {

      int category = Integer.parseInt(servletRequest.getParameter("category"));

      title = category == 1 ? "게시글" : "가입인사";

      Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        servletResponse.sendRedirect("/auth/login");
        return;
      }
      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Board board = boardDao.findBy(key);
      if (board == null) {
        throw new Exception("게시글 번호 오류");
      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("접근 권한이 없습니다.");
      }

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(key);

      txManager.startTransaction();
      attachedFileDao.deleteAll(key);
      boardDao.delete(key);
      txManager.commit();

      for (AttachedFile file : files) {
        new File(this.uploadDir + "/" + file.getFilePath()).delete();
      }

      servletResponse.sendRedirect("/board/list?category=" + category);
    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception ex) {
        servletRequest.setAttribute("message", String.format("%s 삭제 오류!", title));
        servletRequest.setAttribute("exception", e);
        servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
      }
    }
  }
}
