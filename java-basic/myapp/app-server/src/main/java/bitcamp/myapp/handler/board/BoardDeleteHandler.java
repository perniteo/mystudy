package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.util.Prompt;

public class BoardDeleteHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;

  public BoardDeleteHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public BoardDeleteHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    int key = prompt.inputInt("몇 번을 삭제?(1 ~)");

    if (this.boardDao.delete(key) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Delete success");
    }
  }

}
