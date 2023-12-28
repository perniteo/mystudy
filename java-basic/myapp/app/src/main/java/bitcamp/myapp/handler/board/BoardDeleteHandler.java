package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class BoardDeleteHandler implements MenuHandler {

  List<Board> objectRepository;
  Prompt prompt;

  public BoardDeleteHandler(List<Board> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (this.objectRepository.remove(index) == null) {
      System.out.println("유효하지 않은 입력입니다.");
    }
  }

}
