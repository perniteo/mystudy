package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ObjectRepository;

public class BoardListHandler implements MenuHandler {

  ObjectRepository<Board> objectRepository;

  public BoardListHandler(ObjectRepository<Board> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    Board[] boards = new Board[this.objectRepository.size()];
    this.objectRepository.toArray(boards);
    for (Board board : boards) {
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

}
