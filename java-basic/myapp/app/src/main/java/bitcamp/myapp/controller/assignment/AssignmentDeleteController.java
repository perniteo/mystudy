package bitcamp.myapp.controller.assignment;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AssignmentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignmentDeleteController implements PageController {

  AssignmentDao assignmentDao;

  public AssignmentDeleteController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    assignmentDao.delete(Integer.parseInt(request.getParameter("no")));

    return "redirect:list";
  }
}
