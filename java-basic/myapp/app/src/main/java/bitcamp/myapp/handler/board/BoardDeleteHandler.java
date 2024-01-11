package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.util.Prompt;

public class BoardDeleteHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;
//  Prompt prompt;

  public BoardDeleteHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int key = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
//    if (this.objectRepository.remove(index) == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//    }
    if (this.boardDao.delete(key) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Delete success");
    }
  }

}
