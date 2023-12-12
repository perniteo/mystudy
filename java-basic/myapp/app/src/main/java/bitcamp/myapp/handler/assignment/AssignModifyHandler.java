package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignModifyHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignModifyHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = prompt.inputInt("몇 번을 수정?(0~ ) ");
    if (index < 0 || index > this.assignRepository.length) {
      System.out.println("Wrong input");
      return;
    }

    Assignment assignment = this.assignRepository.arr[index];
    assignment.title = this.prompt.input("제목(%s): ", assignment.title);
    assignment.content = this.prompt.input("내용(%s): ", assignment.content);
    assignment.deadline = this.prompt.input("마감기한(%s): ", assignment.deadline);

  }
}
