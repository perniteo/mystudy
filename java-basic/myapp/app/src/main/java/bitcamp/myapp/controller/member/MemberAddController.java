package bitcamp.myapp.controller.member;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class MemberAddController implements PageController {

  private MemberDao memberDao;
  private String uploadDir;

  public MemberAddController(MemberDao memberDao, String uploadDir) {
    this.memberDao = memberDao;
    this.uploadDir = uploadDir;
  }


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/member/form.jsp";
    }

    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));

    Part part = request.getPart("photo");

    if (part.getSize() > 0) {
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      part.write(this.uploadDir + "/" + fileName);
    }

    memberDao.add(member);

    return "redirect:list";
  }
}
