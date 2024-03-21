package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {

  private final Log log = LogFactory.getLog(this.getClass());
  AssignmentDao assignmentDao;

  public AssignmentController(AssignmentDao assignmentDao) {
    log.debug("AssignmentController 생성자");
    this.assignmentDao = assignmentDao;
  }

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(Assignment assignment) throws Exception {
    assignmentDao.add(assignment);

    return "redirect:list";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", assignmentDao.findAll());
  }

  @GetMapping("view")
  public void view(int no, Model model) throws Exception {

    Assignment assignment = assignmentDao.findBy(no);

    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    model.addAttribute("assignment", assignment);
  }

  @PostMapping("update")
  public String update(Assignment assignment) throws Exception {

    assignmentDao.update(assignment);

    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {

    assignmentDao.delete(no);

    return "redirect:list";
  }
}
