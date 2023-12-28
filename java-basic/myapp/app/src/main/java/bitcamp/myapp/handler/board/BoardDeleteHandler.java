package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class BoardDeleteHandler extends AbstractMenuHandler {

  private final List<Board> objectRepository;
//  Prompt prompt;

  public BoardDeleteHandler(List<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
//    if (this.objectRepository.remove(index) == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//    }
    this.objectRepository.remove(index);
  }

}
