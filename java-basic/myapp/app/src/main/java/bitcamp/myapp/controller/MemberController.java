package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.File;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member")
public class MemberController {

  private final Log log = LogFactory.getLog(this.getClass());
  private MemberDao memberDao;
  private String uploadDir;

  public MemberController(MemberDao memberDao, ServletContext servletContext) {
    log.debug("MemberController 생성자");
    this.memberDao = memberDao;
    this.uploadDir = servletContext.getRealPath("/upload");
  }

  @GetMapping("form")
  public String form() {
    return "/member/form.jsp";
  }


  @PostMapping("add")
  public String add(Member member, MultipartFile file) throws Exception {
    if (file.getSize() > 0) {
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      file.transferTo(new File(this.uploadDir + "/" + fileName));
    }

    memberDao.add(member);

    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
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

  @GetMapping("list")
  public String list(Model model) throws Exception {
    model.addAttribute("list", memberDao.findAll());
    return "/member/list.jsp";
  }

  @PostMapping("update")
  public String update(Member member, MultipartFile file) throws Exception {

    if (file.getSize() > 0) {
      new File(this.uploadDir + "/" + member.getPhoto()).delete();
      String fileName = UUID.randomUUID().toString();
      member.setPhoto(fileName);
      file.transferTo(new File(this.uploadDir + "/" + fileName));
    }

    memberDao.update(member);

    return "redirect:list";
  }

  @GetMapping("view")
  public String view(int no, Model model) throws Exception {
    model.addAttribute("member", memberDao.findBy(no));
    return "/member/view.jsp";
  }
}
