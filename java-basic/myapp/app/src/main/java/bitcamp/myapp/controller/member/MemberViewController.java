package bitcamp.myapp.controller.member;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.MemberDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberViewController implements PageController {

  MemberDao memberDao;

  public MemberViewController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("member", memberDao.findBy(Integer.parseInt(request.getParameter("no"))));
    return "/member/view.jsp";
  }
}
