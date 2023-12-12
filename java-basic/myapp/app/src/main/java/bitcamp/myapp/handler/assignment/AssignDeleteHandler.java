package bitcamp.myapp.handler.assignment;

import bitcamp.menu.MenuHandler;
import bitcamp.util.Prompt;

public class AssignDeleteHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignDeleteHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  public void action() throws Exception {
    int index = prompt.inputInt("몇 번을 수정? ");
    if (index < 0 || index > this.assignRepository.length) {
      System.out.println("Wrong input");
      return;
    }
    for (int i = index; i < (this.assignRepository.length - 1); i++) {
      this.assignRepository.arr[i] = this.assignRepository.arr[i + 1];
    }
    this.assignRepository.arr[--this.assignRepository.length] = null;
  }
}
