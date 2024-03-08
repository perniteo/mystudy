package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignmentController {

  AssignmentDao assignmentDao;

  public AssignmentController(AssignmentDao assignmentDao) {
    System.out.println("assignment Controller");
    this.assignmentDao = assignmentDao;
  }

  @RequestMapping("/assignment/form")
  public String form() throws Exception {
    return "/assignment/form.jsp";
  }

  @RequestMapping("/assignment/add")
  public String add(Assignment assignment) throws Exception {
    assignmentDao.add(assignment);

    return "redirect:list";
  }

  @RequestMapping("/assignment/list")
  public String list(Map<String, Object> model) throws Exception {

    model.put("list", assignmentDao.findAll());

    return "/assignment/list.jsp";
  }

  @RequestMapping("/assignment/view")
  public String view(@RequestParam("no") int no, Map<String, Object> model) throws Exception {

    Assignment assignment = assignmentDao.findBy(no);

    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    model.put("assignment", assignment);

    return "/assignment/view.jsp";
  }

  @RequestMapping("/assignment/update")
  public String update(Assignment assignment) throws Exception {

    assignmentDao.update(assignment);

    return "redirect:list";
  }

  @RequestMapping("/assignment/delete")
  public String delete(@RequestParam("no") int no) throws Exception {

    assignmentDao.delete(no);

    return "redirect:list";
  }
}
