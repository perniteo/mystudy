package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardModifyHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;

  public BoardModifyHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public BoardModifyHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {
    int key = prompt.inputInt("몇 번을 변경?(1 ~) ");
    Board oldBoard = this.boardDao.findBy(key);
    if (oldBoard == null) {
      prompt.println("Wrong input number");
      return;
    }
    Board board = new Board();
    board.setNo(oldBoard.getNo());
    board.setTitle(prompt.input("제목(%s) :", oldBoard.getTitle()));
    board.setContent(prompt.input("내용(%s) :", oldBoard.getContent()));
    board.setWriter(prompt.input("작성자(%s) :", oldBoard.getWriter()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    if (this.boardDao.update(board) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Update success");
    }
  }

}
