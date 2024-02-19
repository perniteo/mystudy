package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/form")
public class BoardFormServlet extends HttpServlet {

  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardFormServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.boardDao = new BoardDaoImpl(dbConnectionPool);
    this.attachedFileDao = new AttachedFileDaoImpl(dbConnectionPool);
  }

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
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
    printWriter.printf("<h1>%s</h1>\n", title);

    printWriter.printf("<form action='/board/add?category=%d'>\n", category);
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

    printWriter.println("</body>");
    printWriter.println("</html>");

  }
}
