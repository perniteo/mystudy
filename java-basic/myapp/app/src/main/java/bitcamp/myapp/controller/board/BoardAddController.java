package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BoardAddController implements PageController {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private TransactionManager txManager;
  private String uploadDir;

  public BoardAddController(BoardDao boardDao, AttachedFileDao attachedFileDao,
      TransactionManager txManager, String uploadDir) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
    this.txManager = txManager;
    this.uploadDir = uploadDir;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.parseInt(request.getParameter("category"));
    String title = category == 1 ? "게시글" : "가입 인사";
    request.setAttribute("category", category);
    request.setAttribute("title", title);

    if (request.getMethod().equals("GET")) {
      return "/board/form.jsp";
    }

    try {
      Member member = (Member) request.getSession().getAttribute("loginUser");
      if (member == null) {
        throw new Exception("로그인 하세요");
      }

      ArrayList<AttachedFile> files = new ArrayList<>();

      Collection<Part> parts = request.getParts();

      for (Part part : parts) {
        if (!part.getName().equals("files") || part.getSize() == 0) {
          continue;
        }
        String fileName = UUID.randomUUID().toString();
        part.write(this.uploadDir + "/" + fileName);
        files.add(new AttachedFile().filePath(fileName));
      }

      Board board = new Board();

      board.setCategory(category);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setWriter(member);

      txManager.startTransaction();

      boardDao.add(board);

      if (!files.isEmpty()) {
        for (AttachedFile file : files) {
          file.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(files);
      }

      txManager.commit();

      return "redirect:list?category=" + category;

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
      }
      throw e;
    }
  }
}
