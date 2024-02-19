package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import bitcamp.util.TransactionManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {

  BoardDao boardDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  BoardDao greetingDao;
  MenuGroup mainMenu;
  ExecutorService executorService = Executors.newCachedThreadPool();
  DBConnectionPool dbConnectionPool;
  TransactionManager txManager;
  AttachedFileDao attachedFileDao;

  App() throws Exception {
    prepareDatabase();
//    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
//    new App().run();
    System.out.println("서버 시작!");

    // 톰캣 서버를 구동시키는 객체 준비
    Tomcat tomcat = new Tomcat();

    // 서버의 포트 번호 설정
    tomcat.setPort(7777);

    // 톰캣 서버를 실행하는 동안 사용할 임시 폴더 지정
    tomcat.setBaseDir("./temp");

    // 톰캣 서버의 연결 정보를 설정
    Connector connector = tomcat.getConnector();
    connector.setURIEncoding("UTF-8");

    // 톰캣 서버에 배포할 웹 애플리케이션의 환경 정보 준비
    StandardContext ctx = (StandardContext) tomcat.addWebapp(
        "/", // 컨텍스트 경로(웹 애플리케이션 경로)
        new File("src/main/webapp").getAbsolutePath() // 웹 애플리케이션 파일이 있는 실제 경로
    );
    ctx.setReloadable(true);

    // 웹 애플리케이션 기타 정보 설정
    WebResourceRoot resources = new StandardRoot(ctx);

    // 웹 애플리케이션의 서블릿 클래스 등록
    resources.addPreResources(new DirResourceSet(
        resources, // 루트 웹 애플리케이션 정보
        "/WEB-INF/classes", // 서블릿 클래스 파일의 위치 정보
        new File("build/classes/java/main").getAbsolutePath(), // 서블릿 클래스 파일이 있는 실제 경로
        "/" // 웹 애플리케이션 내부 경로
    ));

    // 웹 애플리케이션 설정 정보를 웹 애플리케이션 환경 정보에 등록
    ctx.setResources(resources);

    // 톰캣 서버 구동
    tomcat.start();

    // 톰캣 서버를 구동한 후 종료될 때까지 JVM을 끝내지 말고 기다린다.
    tomcat.getServer().await();

    System.out.println("서버 종료!");
  }

  void prepareDatabase() {
    try {
      //Driver Class 에서 static 블록으로 등록함 (mySql jar 파일에 포함 돼 있음)
//      Driver driver = new com.mysql.cj.jdbc.Driver();
//      DriverManager.registerDriver(driver);
//      Connection connection = DriverManager.getConnection(
//          "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "bitcamp!@#123"
//          "jdbc:mysql://localhost/studydb", "study", "bitcamp!@#123"
//      );
      dbConnectionPool = new DBConnectionPool(
          "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
          "study", "bitcamp!@#123");

      txManager = new TransactionManager(dbConnectionPool);

      System.out.println("loading");
      System.out.println("success");

      boardDao = new BoardDaoImpl(dbConnectionPool);
      assignmentDao = new AssignmentDaoImpl(dbConnectionPool);
      memberDao = new MemberDaoImpl(dbConnectionPool);
      greetingDao = new BoardDaoImpl(dbConnectionPool);
      attachedFileDao = new AttachedFileDaoImpl(dbConnectionPool);


    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
    }

  }

//  void prepareMenu() {
//    mainMenu = MenuGroup.getInstance("메인");
//
//    mainMenu.addItem("로그인", new LoginHandler(memberDao));
//    mainMenu.addItem("로그아웃", new LogoutHandler());
//
//    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
//    assignmentMenu.addItem("등록", new AssignAddHandler(txManager, assignmentDao));
//    assignmentMenu.addItem("조회", new AssignViewHandler(assignmentDao));
//    assignmentMenu.addItem("변경", new AssignModifyHandler(assignmentDao));
//    assignmentMenu.addItem("삭제", new AssignDeleteHandler(assignmentDao));
//    assignmentMenu.addItem("목록", new AssignListHandler(assignmentDao));
//
//    MenuGroup boardMenu = mainMenu.addGroup("게시글");
//    boardMenu.addItem("등록", new BoardAddHandler(txManager, boardDao, attachedFileDao));
//    boardMenu.addItem("조회", new BoardViewHandler(boardDao, attachedFileDao));
//    boardMenu.addItem("변경", new BoardModifyHandler(boardDao, attachedFileDao));
//    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, attachedFileDao));
//    boardMenu.addItem("목록", new BoardListHandler(boardDao));
//
//    MenuGroup memberMenu = mainMenu.addGroup("회원");
//    memberMenu.addItem("등록", new MemberAddHandler(memberDao));
//    memberMenu.addItem("조회", new MemberViewHandler(memberDao));
//    memberMenu.addItem("변경", new MemberModifyHandler(memberDao));
//    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao));
//    memberMenu.addItem("목록", new MemberListHandler(memberDao));
//
//    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
//    greetingMenu.addItem("등록", new BoardAddHandler(txManager, greetingDao, attachedFileDao));
//    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, attachedFileDao));
//    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, attachedFileDao));
//    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, attachedFileDao));
//    greetingMenu.addItem("목록", new BoardListHandler(greetingDao));
//
//    mainMenu.addItem("도움말", new HelpHandler());
//    mainMenu.addItem("About", new AboutHandler());
//  }

  void run() throws Exception {
    try (ServerSocket serverSocket = new ServerSocket(7777)) {

      while (true) {
        Socket socket = serverSocket.accept();
        executorService.execute(() -> processRequest(socket));
      }

    } catch (Exception e) {
      System.out.println("Server socket err");
      e.printStackTrace();
    }
  }

  void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        Prompt prompt = new Prompt(in, out)) {

      while (true) {
        try {
          mainMenu.execute(prompt);
          prompt.print("[[quit!]]");
          prompt.end();
          break;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    } catch (Exception e) {
      System.out.println();
      e.printStackTrace();
    }
  }

//  void run() throws Exception {
//    while (true) {
//      try {
//        mainMenu.execute(prompt);
//        prompt.close();
//        break;
//      } catch (Exception e) {
//        System.err.println("Exception !");
//      }
//    }
//  }
}

