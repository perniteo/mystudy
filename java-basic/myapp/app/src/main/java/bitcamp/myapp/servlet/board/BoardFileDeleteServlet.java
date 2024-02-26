package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/file/delete")
public class BoardFileDeleteServlet extends HttpServlet {

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

    System.out.println("service() 호출");

    String title = null;

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      servletResponse.sendRedirect("/auth/login");
      return;
    }
    try {
      int category = Integer.parseInt(servletRequest.getParameter("category"));

      title = category == 1 ? "게시글" : "가입인사";

      int fileNo = Integer.parseInt(servletRequest.getParameter("no"));

      AttachedFile file = attachedFileDao.findByNo(fileNo);

      if (file == null) {
        throw new Exception("파일 오류");
      }

      Member writer = boardDao.findBy(file.getBoardNo()).getWriter();

      if (writer.getNo() != loginUser.getNo()) {
        throw new Exception("접근 권한 오류");
      }

      attachedFileDao.delete(fileNo);
    } catch (Exception e) {
      servletRequest.setAttribute("message", String.format("%s 첨부파일 삭제 오류!", title));
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }


  }
}
