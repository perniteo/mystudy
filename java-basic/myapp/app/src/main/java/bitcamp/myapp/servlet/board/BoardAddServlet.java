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

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

  private final TransactionManager txManager;
  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardAddServlet() {
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123"
    );
    this.txManager = new TransactionManager(connectionPool);
    this.boardDao = new BoardDaoImpl(connectionPool, 1);
    this.attachedFileDao = new AttachedFileDaoImpl(connectionPool);
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
    printWriter.println("<h1>게시글</h1>");

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("로그인 후 사용 가능");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    Board board = new Board();
    board.setTitle(servletRequest.getParameter("title"));
    board.setContent(servletRequest.getParameter("content"));
    board.setWriter(loginUser);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    String[] files = servletRequest.getParameterValues("files");
    if (files != null) {
      for (String file : files) {
        if (file.isEmpty()) {
          continue;
        }
        attachedFiles.add(new AttachedFile().filePath(file));
      }
      try {
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

        printWriter.println("<p>게시글 등록 완료</p>");

      } catch (Exception e) {
        try {
          txManager.rollback();
        } catch (Exception e1) {
          printWriter.println("<p>게시들 등록 실패</p>");
        }
      }
      printWriter.println("</body>");
      printWriter.println("</html>");
    }
  }
}
