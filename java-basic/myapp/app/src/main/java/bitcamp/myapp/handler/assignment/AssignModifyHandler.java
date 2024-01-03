package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignModifyHandler extends AbstractMenuHandler {

  private final List<Assignment> objectRepository;
//  Prompt prompt;

  public AssignModifyHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int index = prompt.inputInt("몇 번을 수정?(0~ ) ");

    Assignment oldAssignment = this.objectRepository.get(index);

//    if (oldAssignment == null) {
//      System.out.println("Wrong input");
//      return;
//    }

    Assignment assignment = new Assignment();
    assignment.setTitle(this.prompt.input("제목(%s): ", assignment.getTitle()));
    ;
    assignment.setContent(this.prompt.input("내용(%s): ", assignment.getContent()));
    assignment.setDeadline(oldAssignment.getDeadline());

    objectRepository.set(index, assignment);

  }
}
