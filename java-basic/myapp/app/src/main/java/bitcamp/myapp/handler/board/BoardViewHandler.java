package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardViewHandler extends AbstractMenuHandler {

  //  Prompt prompt;
  private final BoardDao boardDao;

  public BoardViewHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int key = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Board board = this.boardDao.findBy(key);
    if (board == null) {
      System.out.println("Wrong input number");
      return;
    }
//    if (board == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }
    System.out.printf("Key: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());

  }
}
