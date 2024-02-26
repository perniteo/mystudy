package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {

  private BoardDao boardDao;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
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

    printWriter.printf("<a href ='/board/add?category=%d'>새 글</a>\n", category);

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
      servletRequest.setAttribute("message", String.format("%s 목록 오류!", title));
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }

    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
