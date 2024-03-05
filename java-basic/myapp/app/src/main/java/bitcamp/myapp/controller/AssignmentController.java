package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignmentController {

  AssignmentDao assignmentDao;

  public AssignmentController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @RequestMapping("/assignment/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/assignment/form.jsp";
    }

    Assignment assignment = new Assignment();

    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

    assignmentDao.add(assignment);

    return "redirect:list";
  }

  @RequestMapping("/assignment/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setAttribute("list", assignmentDao.findAll());

    return "/assignment/list.jsp";
  }

  @RequestMapping("/assignment/view")
  public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Assignment assignment = assignmentDao.findBy(no);

    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    request.setAttribute("assignment", assignment);

    return "/assignment/view.jsp";
  }

  @RequestMapping("/assignment/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Assignment assignment = assignmentDao.findBy(Integer.parseInt(request.getParameter("no")));

    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

    assignmentDao.update(assignment);

    return "redirect:list";
  }

  @RequestMapping("/assignment/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    assignmentDao.delete(Integer.parseInt(request.getParameter("no")));

    return "redirect:list";
  }
}
