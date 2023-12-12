package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardListHandler implements MenuHandler {

  Prompt prompt;
  BoardRepository boardRepository;

  public BoardListHandler(BoardRepository boardRepository, Prompt prompt) {
    this.boardRepository = boardRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    for (Board board : boardRepository.toArray()) {
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

}
