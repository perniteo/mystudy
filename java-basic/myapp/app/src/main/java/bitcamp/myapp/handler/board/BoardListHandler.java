package bitcamp.myapp.handler.board;

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
  public void action() {
    System.out.println("게시글 목록");
//    System.out.printf("%s 목록을 호출합니다.\n", this.title);
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    for (int i = 0; i < this.boardRepository.length; i++) {
      Board board = this.boardRepository.arr[i];
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

}
