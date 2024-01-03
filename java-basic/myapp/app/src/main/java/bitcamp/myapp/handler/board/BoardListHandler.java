package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.List;

public class BoardListHandler extends AbstractMenuHandler {

  private final List<Board> objectRepository;

  public BoardListHandler(List<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
//    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    Board[] boards = this.objectRepository.toArray(new Board[0]);
//    this.objectRepository.toArray(boards);
    for (Board board : boards) {
      System.out.printf("%-20s%s\t\t%s\n", board.getTitle(), board.getWriter(),
          board.getCreatedDate());
    }
  }

}
