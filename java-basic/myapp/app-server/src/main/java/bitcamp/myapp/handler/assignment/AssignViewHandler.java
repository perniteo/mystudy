package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignViewHandler extends AbstractMenuHandler {

  AssignmentDao assignmentDao;

  public AssignViewHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  public AssignViewHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    int key = prompt.inputInt("몇 번을 조회?(1 ~)");

    Assignment assignment = this.assignmentDao.findBy(key);

    prompt.printf("Key: %s\n", assignment.getNo());
    prompt.printf("제목: %s\n", assignment.getTitle());
    prompt.printf("내용: %s\n", assignment.getContent());
    prompt.printf("마감기한: %s\n", assignment.getDeadline());

  }
}
