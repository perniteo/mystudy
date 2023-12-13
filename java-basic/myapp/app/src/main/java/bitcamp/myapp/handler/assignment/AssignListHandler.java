package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.ObjectRepository;

public class AssignListHandler implements MenuHandler {

  ObjectRepository objectRepository;

  public AssignListHandler(ObjectRepository objectRepository) {
    this.objectRepository = objectRepository;
  }

  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s\t%s\n", "과제명", "제출마감일");
    for (Object object : objectRepository.toArray()) {
      Assignment assignment = (Assignment) object;
      System.out.printf("%-20s\t%s\n", assignment.title, assignment.deadline);
    }
  }
}

