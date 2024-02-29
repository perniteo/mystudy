package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

  private MemberDao memberDao;
  private String uploadDir;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    System.out.println("service() 호출");

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      servletResponse.sendRedirect("/auth/login");
      return;
    }

    try {
      int key = Integer.parseInt(servletRequest.getParameter("no"));

      Member member = memberDao.findBy(key);
      if (member == null) {
        throw new Exception("회원 오류");
      } else if (member.getNo() != loginUser.getNo()) {
        throw new Exception("접근 권한이 없습니다.");
      }

      memberDao.delete(key);

      String filename = member.getPhoto();

      if (filename != null) {
        new File(this.uploadDir + "/" + filename).delete();
      }

      servletResponse.sendRedirect("list");

    } catch (Exception e) {
      servletRequest.setAttribute("message", "삭제 오류!");
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error.jsp").forward(servletRequest, servletResponse);
    }

  }
}
