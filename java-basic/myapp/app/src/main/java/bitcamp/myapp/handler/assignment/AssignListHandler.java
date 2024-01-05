package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignListHandler extends AbstractMenuHandler {

  private final List<Assignment> objectRepository;

  public AssignListHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    System.out.printf("%-18s\t%s\n", "과제명", "제출마감일");

    for (Assignment assignment : this.objectRepository) {
      System.out.printf("%-20s\t%s\n", assignment.getTitle(), assignment.getDeadline());
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

