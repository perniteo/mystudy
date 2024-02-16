package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/file/delete")
public class BoardFileDeleteServlet extends HttpServlet {

  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardFileDeleteServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.boardDao = new BoardDaoImpl(dbConnectionPool, 1);
    this.attachedFileDao = new AttachedFileDaoImpl(dbConnectionPool);
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

    int fileNo = Integer.parseInt(servletRequest.getParameter("no"));

    AttachedFile file = attachedFileDao.findByNo(fileNo);

    if (file == null) {
      printWriter.println("<p>파일 번호 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    Member writer = boardDao.findBy(file.getBoardNo()).getWriter();

    if (writer.getNo() != loginUser.getNo()) {
      printWriter.println("<p>접근 권한이 없습니다</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    attachedFileDao.delete(fileNo);
    printWriter.println("<script>");
    printWriter.println(" location.href = document.referrer;");
    printWriter.println("</script>");

//    List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);
//
//    printWriter.println("<form action='/board/add'>");
//    printWriter.println("<div>");
//    printWriter.println("<label>");
//    printWriter.printf("     제목: <input name='title' type='text' value='%s'>\n", board.getTitle());
//    printWriter.println("  </label>");
//    printWriter.println("</div>");
//    printWriter.println("<div>");
//    printWriter.println("<label>");
//    printWriter.printf(" 내용: <textarea name='content'>%s</textarea>\n", board.getContent());
//    printWriter.println(" </label>");
//    printWriter.println(" </div>");
//    printWriter.println("<div>");
//    printWriter.println("첨부파일: <input multiple name='files' type='file'>");
//    printWriter.println("</div>");
//    printWriter.println("<ul>");
//    for (AttachedFile file : list) {
//      printWriter.printf(" <li>%s<a href = ></li>\n", file.getFilePath());
//    }
//    printWriter.println("</ul>");
//    printWriter.println("<div>");
//    printWriter.println("  <button>변경</button>");
//    printWriter.println("</div>");
//    printWriter.println("</form>");

    printWriter.println("</body>");
    printWriter.println("</html>");

  }
}
