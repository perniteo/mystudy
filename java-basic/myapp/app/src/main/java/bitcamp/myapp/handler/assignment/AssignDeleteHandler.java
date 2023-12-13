package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class AssignDeleteHandler implements MenuHandler {

  ObjectRepository objectRepository;
  Prompt prompt;

  public AssignDeleteHandler(ObjectRepository objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());
    int index = prompt.inputInt("몇 번을 수정? ");
    if (objectRepository.remove(index) == null) {
      System.out.println("Wrong input");
    }
  }
}
