package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignAddHandler extends AbstractMenuHandler {

  private final AssignmentDao assignmentDao;
//  Prompt prompt;

  public AssignAddHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());
//    if (this.assignRepository.length == this.assignRepository.arr.length) {
//      Assignment[] newArr = new Assignment[this.assignRepository.length + (
//          this.assignRepository.length << 1)];
//      System.arraycopy(this.assignRepository.arr, 0, newArr, 0, this.assignRepository.length);
//      this.assignRepository.arr = newArr;
//    }
//    assignment.title = this.prompt.input("%s명? ", title);
    try {
      Assignment assignment = new Assignment();
      assignment.setTitle(this.prompt.input("과제명? "));
      assignment.setContent(this.prompt.input("내용? "));
      assignment.setDeadline(this.prompt.inputDate("제출 마감일? (ex: 2023-12-28) : "));

//    this.assignRepository.arr[this.assignRepository.length++] = assignment;
      assignmentDao.add(assignment);
    } catch (Exception e) {
      System.err.println("Wrong input");
    }
  }

}
