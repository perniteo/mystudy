package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.CookieValue;
import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.controller.RequestParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet(value = "/app/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

  private Map<String, RequestHandler> requestHandlerMap = new HashMap<>();
  //  private List<Object> controllers = new ArrayList<>();
//  private Map<String, Object> beanMap;
  private ApplicationContext applicationContext;

  @Override
  public void init() throws ServletException {
    try {
      System.setProperty("member.upload.dir", this.getServletContext().getRealPath("/upload"));
      System.setProperty("board.upload.dir", this.getServletContext().getRealPath("/upload/board"));
      ApplicationContext parent = (ApplicationContext) this.getServletContext()
          .getAttribute("applicationContext");
      applicationContext = new ClassPathXmlApplicationContext(
          new String[]{"config/app-servlet.xml"}, parent);

      System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
      String[] beans = applicationContext.getBeanDefinitionNames();

      List<Object> controllers = new ArrayList<>();

      for (String bean : beans) {
        if (bean.endsWith("Controller")) {
          controllers.add(applicationContext.getBean(bean));
        }
      }
      prepareRequestHandlers(controllers);

//      ServletContext ctx = this.getServletContext();

//      beanMap = (Map<String, Object>) this.getServletContext().getAttribute("beanMap");

//      applicationContext = new ClassPathXmlApplicationContext("bitcamp.myapp.controller");
//      BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
//      MemberDao memberDao = (MemberDao) ctx.getAttribute("memberDao");
//      AssignmentDao assignmentDao = (AssignmentDao) ctx.getAttribute("assignmentDao");
//      AttachedFileDao attachedFileDao = (AttachedFileDao) ctx.getAttribute("attachedFileDao");
//      TransactionManager txManager = (TransactionManager) ctx.getAttribute("txManager");

//      controllers.add(new HomeController());
//      controllers.add(new AssignmentController(assignmentDao));
//      controllers.add(new AuthController(memberDao));
//      controllers.add(new BoardController(boardDao, attachedFileDao, txManager));
//      controllers.add(new MemberController(memberDao));
//      preparePageControllers();
//      prepareRequestHandlers(applicationContext.getBeans());
    } catch (Exception e) {
      throw new ServletException(e);
    }

  }

  private void prepareRequestHandlers(Collection<Object> controllers) {
    for (Object controller : controllers) {
      Method[] methods = controller.getClass().getDeclaredMethods();
      for (Method m : methods) {
        RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
          requestHandlerMap.put(requestMapping.value(), new RequestHandler(controller, m));
        }
      }
    }
  }

//  private void preparePageControllers() throws Exception {
//    File classpath = new File("./build/classes/java/main");
//    System.out.println(classpath.getCanonicalPath());
//    findComponents(classpath, "");
//  }

//  private void findComponents(File dir, String packageName) throws Exception {
//    File[] files = dir.listFiles(file ->
//        file.isDirectory() || (file.isFile()
//            && !file.getName().contains("$")
//            && file.getName().endsWith(".class")));
//
//    if (!packageName.isEmpty()) {
//      packageName += ".";
//    }
//    for (File file : files) {
//      if (file.isFile()) {
//        Class<?> clazz = Class.forName(packageName + file.getName().replace(".class", ""));
//        Component compAnno = clazz.getAnnotation(Component.class);
//        if (compAnno != null) {
//          Constructor<?> constructor = clazz.getConstructors()[0];
//
//          Parameter[] params = constructor.getParameters();
//          Object[] args = getArguments(params);
//          controllers.add(constructor.newInstance(args));
//          System.out.println(clazz.getName() + " 객체 생성!");
//        }
//      } else {
//        findComponents(file, packageName + file.getName());
//      }
//    }
//  }
//
//  private Object[] getArguments(Parameter[] params) {
//    Object[] args = new Object[params.length];
//    for (int i = 0; i < params.length; i++) {
//      args[i] = findBean(params[i].getType());
//    }
//    return args;
//  }
//
//  private Object findBean(Class<?> type) {
//    Collection<Object> objs = beanMap.values();
//    for (Object obj : objs) {
//      if (type.isInstance(obj)) {
//        return obj;
//      }
//    }
//    return null;
//  }

  private Object valueOf(String value, Class<?> type) {
    if (type == byte.class) {
      return Byte.parseByte(value);
    } else if (type == short.class) {
      return Short.parseShort(value);
    } else if (type == int.class) {
      return Integer.parseInt(value);
    } else if (type == long.class) {
      return Long.parseLong(value);
    } else if (type == float.class) {
      return Float.parseFloat(value);
    } else if (type == double.class) {
      return Double.parseDouble(value);
    } else if (type == boolean.class) {
      return Boolean.parseBoolean(value);
    } else if (type == char.class) {
      return value.charAt(0);
    } else if (type == Date.class) {
      return Date.valueOf(value);
    } else if (type == String.class) {
      return value;
    }
    return null;
  }

  private Object createValueObject(Class<?> type, HttpServletRequest request) throws Exception {
    Constructor<?> constructor = type.getConstructor();

    Object object = constructor.newInstance();

    Method[] methods = type.getDeclaredMethods();

    for (Method method : methods) {
      if (!method.getName().startsWith("set")) {
        continue;
      }
      String propName =
          Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4);

      String requestParamValue = request.getParameter(propName);

      if (requestParamValue != null) {
        method.invoke(object, valueOf(requestParamValue, method.getParameters()[0].getType()));
      }
    }
    return object;
  }

  private String getCookieValue(String name, HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(name)) {
        return cookie.getValue();
      }
    }
    return null;
  }

  private Object[] prepareRequestHandlerArguments(Method m, HttpServletRequest request,
      HttpServletResponse response, Map<String, Object> map) throws Exception {
    Parameter[] params = m.getParameters();
    Object[] args = new Object[params.length];

    for (int i = 0; i < args.length; i++) {
      Parameter param = params[i];
      if (param.getType() == HttpServletRequest.class
          || param.getType() == ServletRequest.class) {
        args[i] = request;
      } else if (param.getType() == HttpServletResponse.class
          || param.getType() == ServletResponse.class) {
        args[i] = response;
      } else if (param.getType() == HttpSession.class) {
        args[i] = request.getSession();
      } else if (param.getType() == Map.class) {
        args[i] = map;
      } else {
        CookieValue cookieValue = param.getAnnotation(CookieValue.class);
        if (cookieValue != null) {
          String cookie = getCookieValue(cookieValue.value(), request);
          if (cookie != null) {
            args[i] = valueOf(cookie, param.getType());
          }
          continue;
        }

        RequestParam requestParam = param.getAnnotation(RequestParam.class);
        if (requestParam != null) {
          String paramName = requestParam.value();

          if (param.getType() == Part.class) {
            Part part = request.getPart(paramName);
            args[i] = part;
          } else if (param.getType() == Part[].class) {
            Collection<Part> parts = request.getParts();
            List<Part> fileParts = new ArrayList<>();
            for (Part part : parts) {
              if (part.getName().equals(paramName)) {
                fileParts.add(part);
              }
            }
            args[i] = fileParts.toArray(new Part[0]);
          } else {
            String paramValue = request.getParameter(paramName);
            args[i] = valueOf(paramValue, param.getType());
          }
          continue;
        }
        args[i] = createValueObject(param.getType(), request);
      }
    }
    return args;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    RequestHandler requestHandler = requestHandlerMap.get(request.getPathInfo());

    if (requestHandler == null) {
      throw new ServletException(request.getPathInfo() + "요청 페이지를 찾을 수 없습니다.");
    }
    Method method = requestHandler.handler;

    Object controller = requestHandler.controller;

    try {
      Map<String, Object> map = new HashMap<>();

      Object[] args = prepareRequestHandlerArguments(method, request, response, map);

      String viewUrl = (String) method.invoke(controller, args);

      map.forEach(request::setAttribute);

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
