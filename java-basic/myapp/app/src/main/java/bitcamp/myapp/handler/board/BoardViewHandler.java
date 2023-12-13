package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class BoardViewHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository<Board> objectRepository;

  public BoardViewHandler(ObjectRepository<Board> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Board board = this.objectRepository.get(index);

    if (board == null) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }

    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createDate);

  }
}
