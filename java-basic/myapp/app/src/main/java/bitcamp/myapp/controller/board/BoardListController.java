package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.BoardDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListController implements PageController {

  BoardDao boardDao;

  public BoardListController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int category = Integer.parseInt(request.getParameter("category"));

    request.setAttribute("title", category == 1 ? "게시글" : "가입 인사");
    request.setAttribute("category", category);
    request.setAttribute("list", boardDao.findAll(category));

    return "/board/list.jsp";
  }
}
