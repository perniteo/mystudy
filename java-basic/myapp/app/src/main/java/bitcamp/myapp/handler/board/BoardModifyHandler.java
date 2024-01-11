package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardModifyHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;
//  Prompt prompt;

  public BoardModifyHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int key = this.prompt.inputInt("몇 번을 변경?(0 ~)");
    Board oldBoard = this.boardDao.findBy(key);
    if (oldBoard == null) {
      System.out.println("Wrong input number");
      return;
    }
//    if (oldBoard == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }
    Board board = new Board();
    board.setNo(oldBoard.getNo());
    board.setTitle(this.prompt.input("제목(%s) :", oldBoard.getTitle()));
    board.setContent(this.prompt.input("내용(%s) :", oldBoard.getContent()));
    board.setWriter(this.prompt.input("작성자(%s) :", oldBoard.getWriter()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    if (this.boardDao.update(board) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Update success");
    }
  }

}
