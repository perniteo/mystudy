package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class AssignDeleteHandler implements MenuHandler {

  ArrayList<Assignment> objectRepository;
  Prompt prompt;

  public AssignDeleteHandler(ArrayList<Assignment> objectRepository, Prompt prompt) {
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
