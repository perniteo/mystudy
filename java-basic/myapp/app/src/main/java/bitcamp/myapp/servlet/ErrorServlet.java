package bitcamp.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {

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

    servletRequest.getRequestDispatcher("/header").include(servletRequest, servletResponse);

    printWriter.println("<h1> 오류 </h1>");
    String message = (String) servletRequest.getAttribute("message");
    if (message != null) {
      printWriter.printf("<p>%s</p>)\n", message);
    }
    Throwable exception = (Throwable) servletRequest.getAttribute("exception");
    if (exception != null) {
      printWriter.println("<pre>");
      exception.printStackTrace();
      printWriter.println("</pre>");

    }

    servletRequest.getRequestDispatcher("/footer").include(servletRequest, servletResponse);

    printWriter.println("</body>");
    printWriter.println("</html>");
  }

}
