package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignViewHandler extends AbstractMenuHandler {

  List<Assignment> objectRepository;
//  Prompt prompt;

  public AssignViewHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Assignment assignment = this.objectRepository.get(index);

//    if (assignment == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }
    System.out.printf("제목: %s\n", assignment.getTitle());
    System.out.printf("내용: %s\n", assignment.getContent());
    System.out.printf("마감기한: %s\n", assignment.getDeadline());

  }
}
