package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class AssignViewHandler implements MenuHandler {

  ArrayList<Assignment> objectRepository;
  Prompt prompt;

  public AssignViewHandler(ArrayList<Assignment> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Assignment assignment = (Assignment) this.objectRepository.get(index);

    if (assignment == null) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    System.out.printf("제목: %s\n", assignment.title);
    System.out.printf("내용: %s\n", assignment.content);
    System.out.printf("마감기한: %s\n", assignment.deadline);

  }
}
