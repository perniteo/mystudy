package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.util.Prompt;

public class AssignDeleteHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;

  public AssignDeleteHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  public AssignDeleteHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  protected void action(Prompt prompt) throws Exception {

    int key = prompt.inputInt("몇 번을 삭제? ");

    if (this.assignmentDao.delete(key) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Delete success");
    }
  }
}
