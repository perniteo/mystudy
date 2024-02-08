package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignModifyHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;

  public AssignModifyHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  public AssignModifyHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    int key = prompt.inputInt("몇 번을 수정?(1~ ) ");

    Assignment oldAssignment = this.assignmentDao.findBy(key);

    if (oldAssignment == null) {
      prompt.println("Wrong input key");
      return;
    }

    Assignment assignment = new Assignment();
    assignment.setNo(oldAssignment.getNo());
    assignment.setTitle(prompt.input("제목(%s): ", oldAssignment.getTitle()));
    assignment.setContent(prompt.input("내용(%s): ", oldAssignment.getContent()));
    assignment.setDeadline(prompt.inputDate("제출 기한(%s): ", oldAssignment.getDeadline()));

    if (this.assignmentDao.update(assignment) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Update success");
    }

  }
}
