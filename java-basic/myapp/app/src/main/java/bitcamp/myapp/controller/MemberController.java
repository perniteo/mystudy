package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Part;

@Component
public class MemberController {

  private MemberDao memberDao;
  private String uploadDir = System.getProperty("member.upload.dir");

  public MemberController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/member/form")
  public String form() {
    return "/member/form.jsp";
  }


  @RequestMapping("/member/add")
  public String add(Member member, @RequestParam("photo") Part part) throws Exception {
    if (part.getSize() > 0) {
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      part.write(this.uploadDir + "/" + fileName);
    }

    memberDao.add(member);

    return "redirect:list";
  }

  @RequestMapping("/member/delete")
  public String delete(@RequestParam("no") int no) throws Exception {
    Member member = memberDao.findBy(no);
    if (member == null) {
      throw new Exception("회원 번호가 유효하지 않습니다.");
    }

    memberDao.delete(no);
    String filename = member.getPhoto();
    if (filename != null) {
      new File(this.uploadDir + "/" + filename).delete();
    }
    return "redirect:list";
  }

  @RequestMapping("/member/list")
  public String list(Map<String, Object> map) throws Exception {
    map.put("list", memberDao.findAll());
    return "/member/list.jsp";
  }

  @RequestMapping("/member/update")
  public String update(Member member, @RequestParam("photo") Part part) throws Exception {

    if (part.getSize() > 0) {
      new File(this.uploadDir + "/" + member.getPhoto()).delete();
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      part.write(this.uploadDir + "/" + fileName);
    }

    memberDao.update(member);

    return "redirect:list";
  }

  @RequestMapping("/member/view")
  public String view(@RequestParam("no") int no, Map<String, Object> map) throws Exception {
    map.put("member", memberDao.findBy(no));
    return "/member/view.jsp";
  }
}
