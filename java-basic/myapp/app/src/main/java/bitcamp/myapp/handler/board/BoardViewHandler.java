package bitcamp.myapp.handler.board;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardViewHandler implements MenuHandler {

  Prompt prompt;
  BoardRepository boardRepository = new BoardRepository();

  public BoardViewHandler(BoardRepository boardRepository, Prompt prompt) {
    this.boardRepository = boardRepository;
    this.prompt = prompt;
  }

  public void action() throws Exception {
    System.out.println("게시글 조회");
//    System.out.printf("%s 조회\n", this.title);
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");
    if (index < 0 || index >= this.boardRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = this.boardRepository.arr[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createDate);

  }
}
