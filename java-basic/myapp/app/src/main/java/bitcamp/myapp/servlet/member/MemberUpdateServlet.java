package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private MemberDao memberDao;
  private TransactionManager txManager;
  private String uploadDir;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {
    servletRequest.setCharacterEncoding("UTF-8");

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
      servletResponse.sendRedirect("/auth/form.html");
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
      servletResponse.setHeader("refresh", "2;url=list");
      printWriter.println("</body>");
      printWriter.println("</html>");
      return;
    }

    member.setName(servletRequest.getParameter("name"));
    member.setEmail(servletRequest.getParameter("email"));
    member.setPassword(servletRequest.getParameter("password"));

    Part photoPart = servletRequest.getPart("photo");
    if (photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      photoPart.write(this.uploadDir + "/" + filename);
    }

    try {
      txManager.startTransaction();

      memberDao.update(member);

      txManager.commit();
      
      servletResponse.sendRedirect("list");
    } catch (Exception e) {
      servletRequest.setAttribute("message", "변경 오류!");
      servletRequest.setAttribute("exception", e);
      servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
    }
  }
}
