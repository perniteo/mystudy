package bitcamp.myapp.servlet;

import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

  @Override
  public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();

    printWriter.println("<header>");
    printWriter.println(
        "  <img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png'>");
    printWriter.println("  <a href='/assignment/list'>과제</a>");
    printWriter.println("  <a href='/board/list?category=1'>게시글</a>");
    printWriter.println("  <a href='/member/list'>회원</a>");
    printWriter.println("  <a href='/board/list?category=2'>가입인사</a>");

    Member loginUser = (Member) req.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      printWriter.println("  <a href='/auth/login'>로그인</a>");
    } else {
      printWriter.printf("  <a href='/auth/logout'>%s 로그아웃</a>\n", loginUser.getName());
    }
    printWriter.println("</header>");

  }

}
