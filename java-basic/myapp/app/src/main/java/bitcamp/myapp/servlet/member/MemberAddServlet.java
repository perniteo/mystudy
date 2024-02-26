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
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;
  private String uploadDir;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = response.getWriter();

    printWriter.println(" <!DOCTYPE html>");
    printWriter.println("<html lang='en'>");
    printWriter.println("<head>");
    printWriter.println(" <meta charset='UTF-8'>");
    printWriter.println("<title>비트캠프 데브옵스 5기</title>");
    printWriter.println("</head>");
    printWriter.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    printWriter.println("<h1>과제 관리 시스템</h1>");

    printWriter.println("<h2>회원</h2>");

    printWriter.println("<form action='/member/add' method='post' enctype='multipart/form-data'>");
    printWriter.println("  <div>");
    printWriter.println("  <label>");
    printWriter.println("      이름: <input name='name' type='text'>");
    printWriter.println("  </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println("  <label>");
    printWriter.println("      이메일: <input name='email' type='text'>");
    printWriter.println(" </label>");
    printWriter.println(" </div>");
    printWriter.println(" <div>");
    printWriter.println("  <label>");
    printWriter.println("   암호:");
    printWriter.println("  <input name='password' type='password'>");
    printWriter.println(" </label>");
    printWriter.println("</div>");
    printWriter.println("사진: <input name='photo' type='file'>");
    printWriter.println("<div>");
    printWriter.println("");
    printWriter.println("</div>");
    printWriter.println("<div>");
    printWriter.println("<button>가입</button>");
    printWriter.println("</div>");
    printWriter.println("</form>");

    request.getRequestDispatcher("/footer").include(request, response);

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {
    servletRequest.setCharacterEncoding("UTF-8");

    Member member = new Member();
    member.setName(servletRequest.getParameter("name"));
    member.setEmail(servletRequest.getParameter("email"));
    member.setPassword(servletRequest.getParameter("password"));

    Part photoPart = servletRequest.getPart("photo");
    if (photoPart.getSize() > 0) {
      // 파일을 선택해서 업로드 했다면,
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      photoPart.write(this.uploadDir + "/" + filename);
    }

    try {
      txManager.startTransaction();

      memberDao.add(member);

      txManager.commit();

      servletResponse.setHeader("refresh", "1;url=index/html");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
        servletRequest.setAttribute("message", "회원 등록 실패");
        servletRequest.setAttribute("exception", e);
        servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
      }
    }

  }
}

