package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class BoardListHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository objectRepository;

  public BoardListHandler(ObjectRepository objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    for (Object object : objectRepository.toArray()) {
      Board board = (Board) object;
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

}
