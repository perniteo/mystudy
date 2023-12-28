package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class AssignDeleteHandler extends AbstractMenuHandler {

  private final List<Assignment> objectRepository;
//  Prompt prompt;

  public AssignDeleteHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int index = prompt.inputInt("몇 번을 삭제? ");
//    if (objectRepository.remove(index) == null) {
//      System.out.println("Wrong input");
//    }
    this.objectRepository.remove(index);
  }
}
