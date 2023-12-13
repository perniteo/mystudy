package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class BoardAddHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository<Board> objectRepository;

  public BoardAddHandler(ObjectRepository<Board> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    Board board = new Board();
    board.title = this.prompt.input("제목: ");
    board.content = this.prompt.input("내용: ");
    board.writer = this.prompt.input("작성자: ");
    board.createDate = this.prompt.input("작성일: ");

    objectRepository.add(board);
  }

}
