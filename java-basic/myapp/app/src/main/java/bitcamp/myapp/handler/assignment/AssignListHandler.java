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
//    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s\t%s\n", "과제명", "제출마감일");

    Assignment[] assignments = this.objectRepository.toArray(new Assignment[0]);

    for (Assignment assignment : assignments) {
//      Assignment assignment = (Assignment) object;
      System.out.printf("%-20s\t%s\n", assignment.getTitle(), assignment.getDeadline());
    }
  }
}

