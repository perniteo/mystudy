package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class AssignModifyHandler implements MenuHandler {

  List<Assignment> objectRepository;
  Prompt prompt;

  public AssignModifyHandler(List<Assignment> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = prompt.inputInt("몇 번을 수정?(0~ ) ");

    Assignment oldAssignment = (Assignment) this.objectRepository.get(index);

    if (oldAssignment == null) {
      System.out.println("Wrong input");
      return;
    }

    Assignment assignment = new Assignment();
    assignment.title = this.prompt.input("제목(%s): ", assignment.title);
    assignment.content = this.prompt.input("내용(%s): ", assignment.content);
    assignment.deadline = this.prompt.input("마감기한(%s): ", assignment.deadline);

    objectRepository.set(index, assignment);

  }
}
