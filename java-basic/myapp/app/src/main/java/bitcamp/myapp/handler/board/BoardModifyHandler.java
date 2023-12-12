package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
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
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 변경?(0 ~)");
    Board oldBoard = this.boardRepository.get(index);
    if (oldBoard == null) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = new Board();
    board.title = this.prompt.input("제목(%s) :", oldBoard.title);
    board.content = this.prompt.input("내용(%s) :", oldBoard.content);
    board.writer = this.prompt.input("작성자(%s) :", oldBoard.writer);
    board.createDate = this.prompt.input("작성일(%s) :", oldBoard.createDate);

    this.boardRepository.set(index, board);
  }

}
