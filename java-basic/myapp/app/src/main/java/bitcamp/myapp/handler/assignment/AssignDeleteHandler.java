package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.util.Prompt;

public class AssignDeleteHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignDeleteHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());
    int index = prompt.inputInt("몇 번을 수정? ");
    if (assignRepository.remove(index) == null) {
      System.out.println("Wrong input");
    }
  }
}
