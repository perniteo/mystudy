package bitcamp.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/footer")
public class FooterServlet extends HttpServlet {

  @Override
  public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter printWriter = resp.getWriter();

    printWriter.println(
        "<footer style='background-color:gray; color:blue; padding:10px; text-align:center;'>");
    printWriter.println("<address>");
    printWriter.println("비트캠프 : 서울 강남구 강남대로 94길 20, 삼오빌딩 5층");
    printWriter.println("</address>");
    printWriter.println("<p>&copy; 2024 네이버 클라우드 데브옵스 5기</p>");
    printWriter.println("</footer>");

  }

}
