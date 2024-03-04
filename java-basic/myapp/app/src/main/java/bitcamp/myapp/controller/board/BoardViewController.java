package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardViewController implements PageController {

  BoardDao boardDao;
  AttachedFileDao attachedFileDao;

  public BoardViewController(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.parseInt(request.getParameter("category"));
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDao.findBy(no);

    request.setAttribute("title", category == 1 ? "게시글" : "가입인사");
    request.setAttribute("category", category);
    request.setAttribute("board", board);
    request.setAttribute("files", attachedFileDao.findAllByBoardNo(no));

    return "/board/view.jsp";
  }
}
