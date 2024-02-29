package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import java.io.IOException;
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
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    String title = null;

    System.out.println("service() 호출");

    try {

      int category = Integer.parseInt(servletRequest.getParameter("category"));

      int key = Integer.parseInt(servletRequest.getParameter("no"));

      title = category == 1 ? "게시글" : "가입인사";

      servletRequest.setAttribute("category", category);
      
      servletRequest.setAttribute("title", title);

      servletRequest.setAttribute("board", boardDao.findBy(key));

      servletRequest.setAttribute("files", attachedFileDao.findAllByBoardNo(key));

      servletRequest.getRequestDispatcher("/board/view.jsp")
          .forward(servletRequest, servletResponse);

//      servletResponse.setContentType("text/html;charset=UTF-8");
//
//      PrintWriter printWriter = servletResponse.getWriter();
//
//      printWriter.println("<!DOCTYPE html>");
//      printWriter.println("<html lang='en'>");
//      printWriter.println("<head>");
//      printWriter.println("  <meta charset='UTF-8'>");
//      printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
//      printWriter.println("</head>");
//      printWriter.println("<body>");
//      servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);
//      printWriter.printf("<h1>%s</h1>\n", title);
//
//      Board board = boardDao.findBy(key);
//      if (board == null) {
//        throw new Exception("게시글 번호 오류");
//      }
//
//      List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);
//
//      printWriter.printf(
//          "<form action='/board/update?category=%d' method='post' enctype='multipart/form-data'>",
//          category);
//      printWriter.printf("카테고리: <input name='category' type='text' value='%d'>\n", category);
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf("  번호: <input readonly name='no' type='text' value='%d'>\n",
//          board.getNo());
//      printWriter.println("  </label>");
//      printWriter.println("</div>");
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf("  제목: <input name='title' type='text' value='%s'>\n", board.getTitle());
//      printWriter.println("  </label>");
//      printWriter.println("</div>");
//      printWriter.println("<div>");
//      printWriter.println("<label>");
//      printWriter.printf(" 내용: <textarea name='content'>%s</textarea>\n", board.getContent());
//      printWriter.println(" </label>");
//      printWriter.println(" </div>");
//      printWriter.println("<div>");
//      printWriter.printf(" 작성자: <input readonly type = 'text' value = '%s'>\n",
//          board.getWriter().getName());
//      printWriter.println(" </div>");
//      printWriter.println("<div>");
//      printWriter.println("첨부파일: <input multiple name='files' type='file'>");
//      printWriter.println("</div>");
//      printWriter.println("<ul>");
//      for (AttachedFile file : list) {
//        printWriter.printf(
//            " <li><a href = '/upload/board/%s'>%1$s</a> [<a href = '/board/file/delete?category=%d&no=%d'>삭제</a>]</li>\n",
//            file.getFilePath(), category, file.getNo());
//      }
//      printWriter.println("</ul>");
//      printWriter.println("<div>");
//      printWriter.println("  <button>변경</button>");
//      printWriter.printf("<a href = '/board/delete?category=%d&no=%d'>[삭제]</a>\n", category, key);
//      printWriter.println("</div>");
//      printWriter.println("</form>");
//
//      servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
//      printWriter.println("</body>");
//      printWriter.println("</html>");
    } catch (Exception e) {
      servletRequest.setAttribute("message", String.format("%s 조회 오류!", title));
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error.jsp").forward(servletRequest, servletResponse);
    }

  }
}
