package bitcamp.myapp.handler.assignment;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignListHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignListHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  public void action() {
    System.out.println("과제 목록");
    System.out.printf("%-18s\t%s\n", "과제명", "제출마감일");
    for (int i = 0; i < this.assignRepository.length; i++) {
      Assignment assignment = this.assignRepository.arr[i];
      System.out.printf("%-20s\t%s\n", assignment.title, assignment.deadline);
    }
  }
}

