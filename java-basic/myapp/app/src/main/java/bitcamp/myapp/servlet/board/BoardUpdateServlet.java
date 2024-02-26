package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private TransactionManager txManager;
  private String uploadDir;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    attachedFileDao = (AttachedFileDao) this.getServletContext().getAttribute("attachedFileDao");
    uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    System.out.println("service() 호출");

    String title = null;
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
        throw new Exception("게시글 오류");
      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("접근 권한이 없습니다.");
      }

      board.setTitle(servletRequest.getParameter("title"));
      board.setContent(servletRequest.getParameter("content"));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      Collection<Part> parts = servletRequest.getParts();

      for (Part part : parts) {
        if (!part.getName().equals("files") || part.getSize() == 0) {
          continue;
        }
        String filename = UUID.randomUUID().toString();
        part.write(this.uploadDir + "/" + filename);
        attachedFiles.add(new AttachedFile().filePath(filename));
      }

      txManager.startTransaction();

      boardDao.update(board);

      if (!attachedFiles.isEmpty()) {
        for (AttachedFile attachedFile : attachedFiles) {
          attachedFile.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }

      txManager.commit();

      servletResponse.sendRedirect("/board/view?category=" + category + "&no=" + key);
    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        servletRequest.setAttribute("message", String.format("%s 변경 오류!", title));
        servletRequest.setAttribute("exception", e);
        servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
      }
    }
  }
}