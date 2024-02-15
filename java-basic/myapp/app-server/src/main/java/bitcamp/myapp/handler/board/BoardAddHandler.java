package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import bitcamp.util.TransactionManager;
import java.util.ArrayList;

public class BoardAddHandler extends AbstractMenuHandler {

  private final TransactionManager txManager;
  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardAddHandler(TransactionManager txManager, BoardDao boardDao,
      AttachedFileDao attachedFileDao) {
    this.txManager = txManager;
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    Member loginUser = (Member) prompt.getSession().getAttr("loginUser");
    if (loginUser == null) {
      prompt.println("로그인 후 사용 가능");
      return;
    }

    Board board = new Board();
    board.setTitle(prompt.input("제목: "));
    board.setContent(prompt.input("내용: "));
    board.setWriter(loginUser);

    ArrayList<AttachedFile> files = new ArrayList<>();
    while (true) {
      String filepath = prompt.input("파일?(종료 : 엔터) : ");

      if (filepath.isEmpty()) {
        break;
      }
      files.add(new AttachedFile().filePath(filepath));
    }
    try {
      txManager.startTransaction();

      boardDao.add(board);

      if (!files.isEmpty()) {
        // 첨부파일 객체에 게시글 번호 저장
        for (AttachedFile file : files) {
          file.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(files);
      }

      txManager.commit();
    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        System.out.println("Error");
      }
    }
  }

}
