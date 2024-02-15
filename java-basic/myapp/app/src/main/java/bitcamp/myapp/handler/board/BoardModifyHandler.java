package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;
import java.util.List;

public class BoardModifyHandler extends AbstractMenuHandler {

  private final BoardDao boardDao;
  private final AttachedFileDao attachedFileDao;

  public BoardModifyHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
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

    int key = prompt.inputInt("몇 번을 변경?(1 ~) ");
    Board oldBoard = this.boardDao.findBy(key);
    if (oldBoard == null) {
      prompt.println("Wrong input number");
      return;
    } else if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
      prompt.println("접근 권한이 없습니다");
      return;
    }
    Board board = new Board();
    board.setNo(oldBoard.getNo());
    board.setTitle(prompt.input("제목(%s) :", oldBoard.getTitle()));
    board.setContent(prompt.input("내용(%s) :", oldBoard.getContent()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    prompt.println("첨부파일:");

    List<AttachedFile> files = attachedFileDao.findAllByBoardNo(key);
    for (AttachedFile file : files) {
      if (prompt.input("  %s 삭제하시겠습니까? (y/N)", file.getFilePath()).equalsIgnoreCase("y")) {
        attachedFileDao.delete(file.getNo());
      }
    }

    List<AttachedFile> newFiles = new ArrayList<>();
    while (true) {
      String filepath = prompt.input("추가할 파일?(종료: 그냥 엔터) ");
      if (filepath.isEmpty()) {
        break;
      }
      newFiles.add(new AttachedFile().filePath(filepath).boardNo(key));
    }

    boardDao.update(board);
    attachedFileDao.addAll(newFiles);

    if (this.boardDao.update(board) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Update success");
    }
  }

}
