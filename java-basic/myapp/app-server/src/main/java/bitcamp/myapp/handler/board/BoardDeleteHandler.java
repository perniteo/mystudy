package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class BoardDeleteHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardDeleteHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    Member loginUser = (Member) prompt.getSession().getAttr("loginUser");

    int key = prompt.inputInt("몇 번을 삭제?(1 ~)");
    Board board = boardDao.findBy(key);

    if (loginUser == null) {
      prompt.println("로그인 후 사용 가능");
      return;
    } else if (board.getWriter().getNo() != loginUser.getNo()) {
      prompt.println("접근 권한이 없습니다");
      return;
    }

    attachedFileDao.deleteAll(key);

    if (this.boardDao.delete(key) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Delete success");
    }
  }

}
