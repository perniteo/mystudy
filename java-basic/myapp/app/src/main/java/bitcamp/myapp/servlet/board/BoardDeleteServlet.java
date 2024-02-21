package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

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

    attachedFileDao.deleteAll(key);
    boardDao.delete(key);

    printWriter.println("<script>");
    printWriter.printf("  location.href = '/board/list?category=%d';\n", category);
    printWriter.println("</script>");
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
