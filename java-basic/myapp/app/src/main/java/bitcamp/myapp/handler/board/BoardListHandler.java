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
//    this.objectRepository.toArray(boards);
//    Board[] boards = this.objectRepository.toArray(new Board[0]);
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");

    for (Board board : this.objectRepository) {
      System.out.printf("%-20s%s\t\t%s\n", board.getTitle(), board.getWriter(),
          board.getCreatedDate());
    }
  }

}
