package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignModifyHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;
//  Prompt prompt;

  public AssignModifyHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int key = prompt.inputInt("몇 번을 수정?(0~ ) ");

    Assignment oldAssignment = this.assignmentDao.findBy(key);

    if (oldAssignment == null) {
      System.out.println("Wrong input key");
      return;
    }

//    if (oldAssignment == null) {
//      System.out.println("Wrong input");
//      return;
//    }

    Assignment assignment = new Assignment();
    assignment.setNo(oldAssignment.getNo());
    assignment.setTitle(this.prompt.input("제목(%s): ", assignment.getTitle()));
    assignment.setContent(this.prompt.input("내용(%s): ", assignment.getContent()));
    assignment.setDeadline(oldAssignment.getDeadline());

//    assignmentDao.update(assignment);
    if (this.assignmentDao.update(assignment) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Update success");
    }

  }
}
