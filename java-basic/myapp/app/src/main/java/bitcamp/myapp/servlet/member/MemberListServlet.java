package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      request.setAttribute("list", memberDao.findAll());
      request.setAttribute("viewUrl", "/member/list.jsp");

//      servletRequest.setAttribute("list", memberDao.findAll());
//
//      servletRequest.getRequestDispatcher("/member/list.jsp")
//          .forward(servletRequest, servletResponse);

    } catch (Exception e) {
      request.setAttribute("exception", e);
//      servletRequest.setAttribute("message", "목록 오류!");
//      servletRequest.setAttribute("exception", e);
//      servletRequest.getRequestDispatcher("/error.jsp").forward(servletRequest, servletResponse);
    }

  }
}
