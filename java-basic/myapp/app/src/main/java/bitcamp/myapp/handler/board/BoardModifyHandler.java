package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class BoardModifyHandler extends AbstractMenuHandler {

  private final List<Board> objectRepository;
//  Prompt prompt;

  public BoardModifyHandler(List<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 변경?(0 ~)");
    Board oldBoard = this.objectRepository.get(index);
//    if (oldBoard == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }
    Board board = new Board();
    board.setTitle(this.prompt.input("제목(%s) :", oldBoard.getTitle()));
    board.setContent(this.prompt.input("내용(%s) :", oldBoard.getContent()));
    board.setWriter(this.prompt.input("작성자(%s) :", oldBoard.getWriter()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    this.objectRepository.set(index, board);
  }

}
