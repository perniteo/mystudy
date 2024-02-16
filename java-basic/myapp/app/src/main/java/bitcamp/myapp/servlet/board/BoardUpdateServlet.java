package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;
  private final TransactionManager txManager;

  public BoardUpdateServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.boardDao = new BoardDaoImpl(dbConnectionPool, 1);
    this.attachedFileDao = new AttachedFileDaoImpl(dbConnectionPool);
    this.txManager = new TransactionManager(dbConnectionPool);
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
    printWriter.println("<h1>게시글</h1>");

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("로그인 하세요");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Board board = boardDao.findBy(key);
    if (board == null) {
      printWriter.println("<p>게시글 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    } else if (board.getWriter().getNo() != loginUser.getNo()) {
      printWriter.println("<p>접근 권한이 없습니다</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    board.setTitle(servletRequest.getParameter("title"));
    board.setContent(servletRequest.getParameter("content"));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    String[] files = servletRequest.getParameterValues("files");
    if (files != null) {
      for (String file : files) {
        if (file.isEmpty()) {
          continue;
        }
        attachedFiles.add(new AttachedFile().filePath(file));
      }
    }
    try {
      txManager.startTransaction();

      boardDao.update(board);

      if (!attachedFiles.isEmpty()) {
        for (AttachedFile attachedFile : attachedFiles) {
          attachedFile.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }

      txManager.commit();

      printWriter.println("<p>게시글을 변경했습니다.</p>");
    } catch (Exception e) {
      printWriter.println("<p>게시글 변경 오류!</p>");
      printWriter.println("<pre>");
      e.printStackTrace(printWriter);
      printWriter.println("</pre>");
    }
  }
}
