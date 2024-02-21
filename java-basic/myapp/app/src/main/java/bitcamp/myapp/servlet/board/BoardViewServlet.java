package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    attachedFileDao = (AttachedFileDao) this.getServletContext().getAttribute("attachedFileDao");
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

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Board board = boardDao.findBy(key);
    if (board == null) {
      printWriter.println("<p>번호 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);

    printWriter.printf("<form action='/board/update?category=%d'>", category);
    printWriter.printf("카테고리: <input name='category' type='text' value='%d'>\n", category);
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  번호: <input readonly name='no' type='text' value='%d'>\n",
        board.getNo());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf("  제목: <input name='title' type='text' value='%s'>\n", board.getTitle());
    printWriter.println("  </label>");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<label>");
    printWriter.printf(" 내용: <textarea name='content'>%s</textarea>\n", board.getContent());
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.printf(" 작성자: <input readonly type = 'text' value = '%s'>\n",
        board.getWriter().getName());
    printWriter.println(" </div>");
    printWriter.println("<div>");
    printWriter.println("첨부파일: <input multiple name='files' type='file'>");
    printWriter.println("</div>");
    printWriter.println("<ul>");
    for (AttachedFile file : list) {
      printWriter.printf(" <li>%s<a href = '/board/file/delete?category=%d&no=%d'>삭제</a></li>\n",
          file.getFilePath(), category, file.getNo());
    }
    printWriter.println("</ul>");
    printWriter.println("<div>");
    printWriter.println("  <button>변경</button>");
    printWriter.printf("<a href = '/board/delete?category=%d&no=%d'>[삭제]</a>\n", category, key);
    printWriter.println("</div>");
    printWriter.println("</form>");

    printWriter.println("</body>");
    printWriter.println("</html>");

  }
}
