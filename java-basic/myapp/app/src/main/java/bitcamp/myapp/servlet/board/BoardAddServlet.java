package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

  private TransactionManager txManager;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    attachedFileDao = (AttachedFileDao) this.getServletContext().getAttribute("attachedFileDao");
    uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    System.out.println("service() 호출");

    int category = Integer.parseInt(servletRequest.getParameter("category"));

    String title = category == 1 ? "게시글" : "가입인사";

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
    printWriter.printf("<h1>%s</h1>\n", title);

    printWriter.printf(
        "<form action='/board/add?category=%d' method='post' enctype='multipart/form-data'>\n",
        category);
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("카테고리: <input readonly name='category' type='text' value='%d'>\n", category);
    printWriter.println(" </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.println("제목: <input name='title' type='text'>");
    printWriter.println(" </label>");
    printWriter.println("</div>");
    printWriter.println("  <div>");
    printWriter.println(" <label>");
    printWriter.println("   내용: <textarea name='content'></textarea>");
    printWriter.println(" </label>");
    printWriter.println("  </div>");
    printWriter.println("  <div>");
    printWriter.println("       첨부파일: <input multiple name='files' type='file'>");
    printWriter.println(" </div>");
    printWriter.println("   <button>등록</button>");
    printWriter.println("</form>");

    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
    printWriter.println("</body>");
    printWriter.println("</html>");

  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {
    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    String title = null;
    if (loginUser == null) {
      servletResponse.sendRedirect("/auth/login");
      return;
    }
    try {
      int category = Integer.parseInt(servletRequest.getParameter("category"));

      title = category == 1 ? "게시글" : "가입인사";

      Board board = new Board();
      board.setCategory(category);
      board.setTitle(servletRequest.getParameter("title"));
      board.setContent(servletRequest.getParameter("content"));
      board.setWriter(loginUser);

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

      boardDao.add(board);

      if (!attachedFiles.isEmpty()) {
        // 첨부파일 객체에 게시글 번호 저장
        for (AttachedFile file : attachedFiles) {
          file.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }
      txManager.commit();

      servletResponse.sendRedirect("/board/list?category=" + category);
    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        servletRequest.setAttribute("message", String.format("%s 등록 오류!", title));
        servletRequest.setAttribute("exception", e);
        servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
      }
    }
  }
}
