package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/list")
public class BoardListServlet extends GenericServlet {

  private final BoardDao boardDao;

  public BoardListServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.boardDao = new BoardDaoImpl(dbConnectionPool);
  }


  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
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

    printWriter.printf("<a href ='/board/form?category=%d'>새 글</a>\n", category);

    try {
      printWriter.println("<table border='1'>");
      printWriter.println("    <thead>");
      printWriter.println(
          "    <tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>첨부파일</th> </tr>");
      printWriter.println("    </thead>");
      printWriter.println("    <tbody>");

      List<Board> list = boardDao.findAll(category);

      for (Board board : list) {
        printWriter.printf(
            "<tr> <td>%d</td> <td><a href = '/board/view?category=%d&no=%1$d'>%s</td> <td>%s</td> <td>%s</td> <td>%d</td> </tr>\n",
            board.getNo(),
            category,
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getFileCount());
      }

      printWriter.println("    </tbody>");
      printWriter.println("</table>");

    } catch (Exception e) {
      printWriter.println("<p>목록 오류!</p>");
      printWriter.println("<pre>");
      e.printStackTrace(printWriter);
      printWriter.println("</pre>");
    }

    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
