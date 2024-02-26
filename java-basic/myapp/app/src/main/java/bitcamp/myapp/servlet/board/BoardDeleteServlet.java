package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
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
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    String title = null;

    System.out.println("service() 호출");
    try {

      int category = Integer.parseInt(servletRequest.getParameter("category"));

      title = category == 1 ? "게시글" : "가입인사";

      Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        servletResponse.sendRedirect("/auth/login");
        return;
      }
      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Board board = boardDao.findBy(key);
      if (board == null) {
        throw new Exception("게시글 번호 오류");
      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("접근 권한이 없습니다.");
      }

      attachedFileDao.deleteAll(key);
      boardDao.delete(key);
      servletResponse.sendRedirect("/board/list?category=" + category);
    } catch (Exception e) {
      servletRequest.setAttribute("message", String.format("%s 삭제 오류!", title));
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }

  }
}
