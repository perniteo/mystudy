package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.Date;

public class BoardAddHandler extends AbstractMenuHandler {

  //  Prompt prompt;
  private final BoardDao boardDao;

  public BoardAddHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    Board board = new Board();
    board.setTitle(this.prompt.input("제목: "));
    board.setContent(this.prompt.input("내용: "));
    board.setWriter(this.prompt.input("작성자: "));
    board.setCreatedDate(new Date());

    boardDao.add(board);
  }

}
