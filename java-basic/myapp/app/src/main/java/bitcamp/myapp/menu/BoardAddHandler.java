package bitcamp.myapp.menu;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardAddHandler implements MenuHandler {

  //
//  String title;
//  Board[] arr = new Board[3];
//  int length = 0;
  Prompt prompt;
  BoardRepository boardRepository;

  public BoardAddHandler(BoardRepository boardRepository, Prompt prompt) {
    this.boardRepository = boardRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() throws Exception {
    if (this.boardRepository.length == this.boardRepository.arr.length) {
      Board[] newArr = new Board[this.boardRepository.length + (this.boardRepository.length / 2)];
      System.arraycopy(this.boardRepository.arr, 0, newArr, 0, this.boardRepository.length);
      this.boardRepository.arr = newArr;
    }
//    System.out.printf("%s 등록:\n", this.title);

    Board board = new Board();
    board.title = this.prompt.input("제목: ");
    board.content = this.prompt.input("내용: ");
    board.writer = this.prompt.input("작성자: ");
    board.createDate = this.prompt.input("작성일: ");
    this.boardRepository.arr[this.boardRepository.length++] = board;
  }

}
