package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardListHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public BoardListHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) {
    prompt.printf("%-4s\t%-15s%s\t%s\t%s\n", "Key", "제목", "작성자", "작성일", "파일 수");

    for (Board board : boardDao.findAll()) {
      prompt.printf("%-4s\t%-17s%s\t\t%s\t%5$d\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter(),
          board.getCreatedDate(),
          board.getFileCount());

    }
  }

}
