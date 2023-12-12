package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.util.Prompt;

public class BoardDeleteHandler implements MenuHandler {

  BoardRepository boardRepository;
  Prompt prompt;

  public BoardDeleteHandler(BoardRepository boardRepository, Prompt prompt) {
    this.boardRepository = boardRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (this.boardRepository.remove(index) == null) {
      System.out.println("유효하지 않은 입력입니다.");
    }
  }

}
