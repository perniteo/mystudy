package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.BoardDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListController {

  BoardDao boardDao;

  public BoardListController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int category = Integer.parseInt(request.getParameter("category"));

    request.setAttribute("title", category == 1 ? "게시글" : "가입 인사");
    request.setAttribute("category", category);
    request.setAttribute("list", boardDao.findAll(category));

    return "/board/list.jsp";
  }
}
