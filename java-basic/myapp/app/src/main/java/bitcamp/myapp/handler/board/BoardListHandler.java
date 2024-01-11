package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardListHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() {
//    System.out.printf("[%s]", menu.getTitle());
//    this.objectRepository.toArray(boards);
//    Board[] boards = this.objectRepository.toArray(new Board[0]);
    System.out.printf("%-4s\t%-18s%s\t%s\n", "Key", "제목", "작성자", "작성일");

    for (Board board : this.boardDao.findAll()) {
      System.out.printf("%-4s\t%-20s%s\t\t%s\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter(),
          board.getCreatedDate());
    }
  }

}
