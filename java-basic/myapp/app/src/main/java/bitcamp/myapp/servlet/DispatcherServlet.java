package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.AssignmentController;
import bitcamp.myapp.controller.AuthController;
import bitcamp.myapp.controller.BoardController;
import bitcamp.myapp.controller.HomeController;
import bitcamp.myapp.controller.MemberController;
import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private List<Object> controllers = new ArrayList<>();

  @Override
  public void init() throws ServletException {
    ServletContext ctx = this.getServletContext();
    BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
    MemberDao memberDao = (MemberDao) ctx.getAttribute("memberDao");
    AssignmentDao assignmentDao = (AssignmentDao) ctx.getAttribute("assignmentDao");
    AttachedFileDao attachedFileDao = (AttachedFileDao) ctx.getAttribute("attachedFileDao");
    TransactionManager txManager = (TransactionManager) ctx.getAttribute("txManager");

    String memberUploadDir = this.getServletContext().getRealPath("/upload");
    String boardUploadDir = this.getServletContext().getRealPath("/upload/board");

    controllers.add(new HomeController());
    controllers.add(new AuthController(memberDao));
    controllers.add(new AssignmentController(assignmentDao));
    controllers.add(new BoardController(boardDao, attachedFileDao, txManager, boardUploadDir));
    controllers.add(new MemberController(memberDao, memberUploadDir));


  }

  private Method findRequestHandler(Object controller, String pathInfo) {
    Method[] methods = controller.getClass().getDeclaredMethods();
    for (Method m : methods) {
      RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);

      if (m != null && requestMapping.value().equals(pathInfo)) {
        return m;
      }
    }
    return null;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Object controller = null;
    Method requestHandler = null;

    for (Object obj : controllers) {
      requestHandler = findRequestHandler(obj, request.getPathInfo());
      if (requestHandler != null) {
        controller = obj;
        break;
      }
    }

    if (requestHandler == null) {
      throw new ServletException(request.getPathInfo() + "요청 페이지를 찾을 수 없습니다.");
    }
    try {
      String viewUrl = (String) requestHandler.invoke(controller, request, response);

      // 페이지 컨트롤러가 알려준 JSP로 포워딩 한다.
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).forward(request, response);
      }

    } catch (Exception e) {
      // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
      request.setAttribute("message", request.getPathInfo() + " 실행 오류!");

      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      request.setAttribute("detail", stringWriter.toString());

      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

//    // URL에서 요청한 페이지 컨트롤러를 실행한다.
//    request.getRequestDispatcher(request.getPathInfo()).include(request, response);
//
//    // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
//    Throwable exception = (Throwable) request.getAttribute("exception");
//    if (exception != null) {
//      request.setAttribute("message", request.getPathInfo() + " 실행 오류!");
//
//      StringWriter stringWriter = new StringWriter();
//      PrintWriter printWriter = new PrintWriter(stringWriter);
//      exception.printStackTrace(printWriter);
//      request.setAttribute("detail", stringWriter.toString());
//
//      request.getRequestDispatcher("/error.jsp").forward(request, response);
//      return;
//    }
//
//    // 페이지 컨트롤러에서 만든 쿠키가 있다면 응답에 포함시킨다.
//    List<Cookie> cookies = (List<Cookie>) request.getAttribute("cookies");
//    if (cookies != null) {
//      for (Cookie cookie : cookies) {
//        response.addCookie(cookie);
//      }
//    }
//
//    // 페이지 컨트롤러가 알려준 JSP로 포워딩 한다.
//    String viewUrl = (String) request.getAttribute("viewUrl");
//    if (viewUrl.startsWith("redirect:")) {
//      response.sendRedirect(viewUrl.substring(9));
//    } else {
//      request.getRequestDispatcher(viewUrl).forward(request, response);
//    }
  }
}
