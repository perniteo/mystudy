package bitcamp.myapp.controller;

import bitcamp.myapp.service.AssignmentService;
import bitcamp.myapp.vo.Assignment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/assignment")
public class AssignmentController {

  private final Log log = LogFactory.getLog(this.getClass());
  private final AssignmentService assignmentService;

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(Assignment assignment) throws Exception {
    assignmentService.add(assignment);

    return "redirect:list";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", assignmentService.list());
  }

  @GetMapping("view")
  public void view(int no, Model model) throws Exception {

    Assignment assignment = assignmentService.get(no);

    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    model.addAttribute("assignment", assignment);
  }

  @PostMapping("update")
  public String update(Assignment assignment) throws Exception {

    assignmentService.update(assignment);

    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {

    assignmentService.delete(no);

    return "redirect:list";
  }
}
