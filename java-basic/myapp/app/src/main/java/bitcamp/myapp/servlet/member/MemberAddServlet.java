package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    servletResponse.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = servletResponse.getWriter();

    printWriter.println("<!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println("  <meta charset='UTF-8'>");
    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");
    printWriter.println("<h1>회원</h1>");

//    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
//    if (loginUser == null) {
//      printWriter.println("로그인 후 사용 가능");
//      printWriter.println("</body>");
//      printWriter.println("</html>");
//      return;
//    }

    Member member = new Member();
    member.setName(servletRequest.getParameter("name"));
    member.setEmail(servletRequest.getParameter("email"));
    member.setPassword(servletRequest.getParameter("password"));

//    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

//    String[] files = servletRequest.getParameterValues("files");
//    if (files != null) {
//      for (String file : files) {
//        if (file.isEmpty()) {
//          continue;
//        }
//        attachedFiles.add(new AttachedFile().filePath(file));
//      }
    try {
      txManager.startTransaction();

      memberDao.add(member);

//        if (!attachedFiles.isEmpty()) {
//          // 첨부파일 객체에 게시글 번호 저장
//          for (AttachedFile file : attachedFiles) {
//            file.setBoardNo(board.getNo());
//          }
//          attachedFileDao.addAll(attachedFiles);
//        }
      txManager.commit();

      printWriter.println("<p>회원 가입 완료</p>");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        printWriter.println("<p>회원 가입 실패</p>");
      }
    }
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}

