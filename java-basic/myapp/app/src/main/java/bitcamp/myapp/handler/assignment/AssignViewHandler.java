package bitcamp.myapp.handler.assignment;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignViewHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignViewHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() throws Exception {
    System.out.println("과제 조회");
//    System.out.printf("%s 조회\n", this.title);
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");
    if (index < 0 || index >= this.assignRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Assignment assignment = this.assignRepository.arr[index];
    System.out.printf("제목: %s\n", assignment.title);
    System.out.printf("내용: %s\n", assignment.content);
    System.out.printf("마감기한: %s\n", assignment.deadline);

  }
}
