package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private final MemberDao memberDao;
  private final TransactionManager txManager;

  public MemberUpdateServlet() {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");
    this.memberDao = new MemberDaoImpl(dbConnectionPool);
    this.txManager = new TransactionManager(dbConnectionPool);
  }

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {

    System.out.println("service() 호출");

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

    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("로그인 하세요");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    int key = Integer.parseInt(servletRequest.getParameter("no"));

    Member member = memberDao.findBy(key);
    if (member == null) {
      printWriter.println("<p>회원 오류</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    } else if (member.getNo() != loginUser.getNo()) {
      printWriter.println("<p>접근 권한이 없습니다</p>");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

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
//    }
    try {
      txManager.startTransaction();

      memberDao.update(member);

//      if (!attachedFiles.isEmpty()) {
//        for (AttachedFile attachedFile : attachedFiles) {
//          attachedFile.setBoardNo(board.getNo());
//        }
//        attachedFileDao.addAll(attachedFiles);
//      }

      txManager.commit();

      printWriter.println("<p>회원 정보를 변경했습니다.</p>");
    } catch (Exception e) {
      printWriter.println("<p>회원 변경 오류!</p>");
      printWriter.println("<pre>");
      printWriter.println(member.getName() + member.getEmail() + member.getPassword());
      e.printStackTrace(printWriter);
      printWriter.println("</pre>");
    }
  }
}
