package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class BoardViewHandler extends AbstractMenuHandler {

  //  Prompt prompt;
  private final List<Board> objectRepository;

  public BoardViewHandler(List<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Board board = this.objectRepository.get(index);

//    if (board == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());

  }
}
