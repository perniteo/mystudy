package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.List;

public class BoardViewHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  public BoardViewHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  public BoardViewHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {
    int key = prompt.inputInt("몇 번을 조회?(1 ~)");

    Board board = boardDao.findBy(key);
    if (board == null) {
      prompt.println("Wrong input number");
      return;
    }

    List<AttachedFile> list = attachedFileDao.findAllByBoardNo(key);

    prompt.printf("Key: %d\n", board.getNo());
    prompt.printf("제목: %s\n", board.getTitle());
    prompt.printf("내용: %s\n", board.getContent());
    prompt.printf("작성자: %s\n", board.getWriter());
    prompt.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());

    prompt.println("첨부파일: ");

    for (AttachedFile file : list) {
      prompt.printf("\t%s\n", file.getFilePath());
    }

  }
}
