package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

  private final Log log = LogFactory.getLog(this.getClass());
  private final BoardService boardService;
  private final StorageService storageService;
  private String uploadDir = "board/";

  @Value("${ncp.ss.bucketname}")
  private String bucketName;


  @GetMapping("form")
  public void form(int category, Model model)
      throws Exception {
    model.addAttribute("category", category);
    model.addAttribute("title", category == 1 ? "게시글" : "가입 인사");
  }


  @PostMapping("add")
  public String add(Board board,
      MultipartFile[] attachedFiles,
      Model model,
      HttpSession session) throws Exception {

    int category = board.getCategory();
    log.debug(category);
    model.addAttribute("category", category);

    Member member = (Member) session.getAttribute("loginUser");
    if (member == null) {
      throw new Exception("로그인 하세요");
    }

    ArrayList<AttachedFile> files = new ArrayList<>();

    for (MultipartFile part : attachedFiles) {
      if (part.getSize() == 0) {
        continue;
      }
      String filename = storageService.upload(this.bucketName, this.uploadDir, part);
      files.add(AttachedFile.builder().filePath(filename).build());
    }
    board.setWriter(member);

    if (!files.isEmpty()) {
      board.setFiles(files);
    }
    boardService.add(board);

    return "redirect:list";

  }


  @GetMapping("delete")
  public String delete(int category, HttpSession session,
      int no) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");

    } else if (board.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }
    log.debug(no);

    List<AttachedFile> files = boardService.getAttachedFiles(no);

    boardService.delete(no);

    for (AttachedFile file : files) {
      new File(this.uploadDir + "/" + file.getFilePath()).delete();
    }

    return "redirect:list?category=" + category;
  }


  @GetMapping("file/delete")
  public String fileDelete(int category, int no,
      HttpSession session)
      throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    AttachedFile file = boardService.getAttachedFile(no);
    if (file == null) {
      throw new Exception("첨부파일 번호가 유효하지 않습니다.");
    }

    Member writer = boardService.get(file.getBoardNo()).getWriter();
    if (writer.getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    boardService.deleteAttachedFile(no);
    new File(this.uploadDir + "/" + file.getFilePath()).delete();

    return "redirect:../view?category=" + category + "&no=" + file.getBoardNo();

  }

  @GetMapping("list")
  public void list(int category, Model model)
      throws Exception {

    model.addAttribute("title", category == 1 ? "게시글" : "가입 인사");
    model.addAttribute("category", category);
    model.addAttribute("list", boardService.list(category));
  }

  @PostMapping("update")
  public String update(Board board,
      MultipartFile[] attachedFiles,
      HttpSession session,
      Model model)
      throws Exception {

    model.addAttribute("category", board.getCategory());

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }
    Board old = boardService.get(board.getNo());
    if (old == null) {
      throw new Exception("번호가 유효하지 않습니다.");

    } else if (old.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    ArrayList<AttachedFile> files = new ArrayList<>();

    for (MultipartFile part : attachedFiles) {
      if (part.getSize() == 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(this.uploadDir + "/" + filename));
      files.add(AttachedFile.builder().filePath(filename).build());
    }

    if (!files.isEmpty()) {
      board.setFiles(files);
    }
    boardService.update(board);
    return "redirect:list";

  }

  @GetMapping("view")
  public void view(int category, int no,
      Model model) throws Exception {

    Board board = boardService.get(no);

    if (board == null) {
      throw new Exception("번호 오류");
    }

    model.addAttribute("title", category == 1 ? "게시글" : "가입인사");
    model.addAttribute("category", category);
    model.addAttribute("board", board);
//    model.addAttribute("files", boardService.getAttachedFiles(no));
  }
}