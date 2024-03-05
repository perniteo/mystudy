package bitcamp.myapp.controller.member;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.File;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class MemberUpdateController {

  private MemberDao memberDao;
  private String uploadDir;

  public MemberUpdateController(MemberDao memberDao, String uploadDir) {
    this.memberDao = memberDao;
    this.uploadDir = uploadDir;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Member member = memberDao.findBy(no);

    member.setEmail(request.getParameter("email"));
    member.setName(request.getParameter("name"));
    member.setPassword(request.getParameter("password"));

    Part part = request.getPart("photo");

    if (part.getSize() > 0) {
      new File(this.uploadDir + "/" + member.getPhoto()).delete();
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      part.write(this.uploadDir + "/" + fileName);
    }

    memberDao.update(member);

    return "redirect:list";
  }
}
