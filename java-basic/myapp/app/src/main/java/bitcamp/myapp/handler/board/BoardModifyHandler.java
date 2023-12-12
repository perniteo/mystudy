package bitcamp.myapp.handler.board;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardModifyHandler implements MenuHandler {

  BoardRepository boardRepository;
  Prompt prompt;

  public BoardModifyHandler(BoardRepository boardRepository, Prompt prompt) {
    this.boardRepository = boardRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() throws Exception {
    System.out.println("게시글 수정");
//    System.out.printf("%s 변경\n", this.title);
    int index = this.prompt.inputInt("몇 번을 변경?(0 ~");
    if (index < 0 || index >= this.boardRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = this.boardRepository.arr[index];
    board.title = this.prompt.input("제목(%s) :", board.title);
    board.content = this.prompt.input("내용(%s) :", board.content);
    board.writer = this.prompt.input("작성자(%s) :", board.writer);
    board.createDate = this.prompt.input("작성일(%s) :", board.createDate);
  }

}
