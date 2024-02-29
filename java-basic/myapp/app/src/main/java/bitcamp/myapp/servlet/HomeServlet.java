package bitcamp.myapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws ServletException, IOException {
    servletRequest.getRequestDispatcher("/home.jsp").forward(servletRequest, servletResponse);

//    servletResponse.setContentType("text/html;charset=UTF-8");
//
//    PrintWriter printWriter = servletResponse.getWriter();
//
//    printWriter.println("<!DOCTYPE html>");
//    printWriter.println("<html lang='en'>");
//    printWriter.println("<head>");
//    printWriter.println("  <meta charset='UTF-8'>");
//    printWriter.println("  <title>비트캠프 데브옵스 5기</title>");
//    printWriter.println("</head>");
//    printWriter.println("<body>");
//
//    servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);
//
//    printWriter.println("<h1>과제 관리 시스템</h1>");
//
//    Member loginUser = (Member) servletRequest.getSession().getAttribute("loginUser");
//
//    if (loginUser == null) {
//      printWriter.println("<p>환영합니다 ^^</p>");
//    } else {
//      printWriter.printf("<p>환영합니다 %s님^^</p>\n", loginUser.getName());
//    }
//
//    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);
//
//    printWriter.println("</body>");
//    printWriter.println("</html>");
  }
}
