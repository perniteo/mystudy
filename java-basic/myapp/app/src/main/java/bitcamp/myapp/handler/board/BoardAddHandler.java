package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.Date;
import java.util.List;

public class BoardAddHandler extends AbstractMenuHandler {

  //  Prompt prompt;
  private final List<Board> objectRepository;

  public BoardAddHandler(List<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    Board board = new Board();
    board.setTitle(this.prompt.input("제목: "));
    board.setContent(this.prompt.input("내용: "));
    board.setWriter(this.prompt.input("작성자: "));
    board.setCreatedDate(new Date());

    objectRepository.add(board);
  }

}
