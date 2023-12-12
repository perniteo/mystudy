package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;

public class AssignAddHandler implements MenuHandler {

  AssignRepository assignRepository;
  Prompt prompt;

  public AssignAddHandler(AssignRepository assignRepository, Prompt prompt) {
    this.assignRepository = assignRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());
    if (this.assignRepository.length == this.assignRepository.arr.length) {
      Assignment[] newArr = new Assignment[this.assignRepository.length + (
          this.assignRepository.length << 1)];
      System.arraycopy(this.assignRepository.arr, 0, newArr, 0, this.assignRepository.length);
      this.assignRepository.arr = newArr;
    }
    Assignment assignment = new Assignment();
//    assignment.title = this.prompt.input("%s명? ", title);
    assignment.title = this.prompt.input("과제명? ");
    assignment.content = this.prompt.input("내용? ");
    assignment.deadline = this.prompt.input("제출 마감일? ");

    this.assignRepository.arr[this.assignRepository.length++] = assignment;
  }

}
