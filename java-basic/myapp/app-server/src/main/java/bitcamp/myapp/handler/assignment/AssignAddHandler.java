package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignAddHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;

  public AssignAddHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  public AssignAddHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {
    Assignment assignment = new Assignment();
    assignment.setTitle(prompt.input("과제명? "));
    assignment.setContent(prompt.input("내용? "));
    assignment.setDeadline(prompt.inputDate("제출 마감일? (ex: 2023-12-28) : "));

    assignmentDao.add(assignment);
  }

}
