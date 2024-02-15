import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class HelloServlet implements Servlet {

  public HelloServlet() {
    System.out.println("Created Hello Servlet");
  }

  @Override
  public void init(ServletConfig servletConfig) throws ServletException {
    System.out.println("init() 호출");
  }

  @Override
  public ServletConfig getServletConfig() {
    System.out.println("getServletConfig() 호출");
    return null;
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {
    System.out.println("service() 호출");

    servletResponse.setContentType("text/plain;charset=UTF-8");
    PrintWriter printWriter = servletResponse.getWriter();
    printWriter.println("Hello");
  }

  @Override
  public String getServletInfo() {
    System.out.println("getServletInfo() 호출");
    return null;
  }

  @Override
  public void destroy() {
    System.out.println("destroy() 호출");

  }
}
