package bitcamp.myapp.handler.board;

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
  public void action() throws Exception {
    System.out.println("게시글 삭제");
//    System.out.printf("%s 삭제\n", this.title);

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (index < 0 || index >= this.boardRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    for (int i = index; i < (this.boardRepository.length - 1); i++) {
      this.boardRepository.arr[i] = this.boardRepository.arr[i + 1];
    }
    this.boardRepository.arr[--boardRepository.length] = null;
  }

}
