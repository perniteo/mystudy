package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignListHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;

  public AssignListHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-4s\t%-18s\t%s\n", "Key", "과제명", "제출마감일");

    for (Assignment assignment : this.assignmentDao.findAll()) {
      System.out.printf("%-4s\t%-20s\t%s\n",
          assignment.getNo(),
          assignment.getTitle(),
          assignment.getDeadline());
    }
//    System.out.printf("[%s]", menu.getTitle());
//    Assignment[] assignments = this.objectRepository.toArray(new Assignment[0]);
//    Iterator<Assignment> iterator = this.objectRepository.iterator();

//    while (iterator.hasNext()) {
//      Assignment assignment = iterator.next();
//      System.out.printf("%-20s\t%s\n", assignment.getTitle(), assignment.getDeadline());
//    }

//    for (Assignment assignment : assignments) {
//      Assignment assignment = (Assignment) object;
//      System.out.printf("%-20s\t%s\n", assignment.getTitle(), assignment.getDeadline());
//    }
  }
}

