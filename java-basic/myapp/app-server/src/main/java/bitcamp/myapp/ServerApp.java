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
import bitcamp.myapp.handler.AboutHandler;
import bitcamp.myapp.handler.HelpHandler;
import bitcamp.myapp.handler.assignment.AssignAddHandler;
import bitcamp.myapp.handler.assignment.AssignDeleteHandler;
import bitcamp.myapp.handler.assignment.AssignListHandler;
import bitcamp.myapp.handler.assignment.AssignModifyHandler;
import bitcamp.myapp.handler.assignment.AssignViewHandler;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.myapp.handler.member.MemberAddHandler;
import bitcamp.myapp.handler.member.MemberDeleteHandler;
import bitcamp.myapp.handler.member.MemberListHandler;
import bitcamp.myapp.handler.member.MemberModifyHandler;
import bitcamp.myapp.handler.member.MemberViewHandler;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import bitcamp.util.TransactionManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {

  BoardDao boardDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  BoardDao greetingDao;
  MenuGroup mainMenu;
  ExecutorService executorService = Executors.newCachedThreadPool();
  DBConnectionPool dbConnectionPool;
  TransactionManager txManager;
  AttachedFileDao attachedFileDao;

  ServerApp() throws Exception {
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
    new ServerApp().run();
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

      boardDao = new BoardDaoImpl(dbConnectionPool, 1);
      assignmentDao = new AssignmentDaoImpl(dbConnectionPool);
      memberDao = new MemberDaoImpl(dbConnectionPool);
      greetingDao = new BoardDaoImpl(dbConnectionPool, 2);
      attachedFileDao = new AttachedFileDaoImpl(dbConnectionPool);


    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
    }

  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignAddHandler(txManager, assignmentDao));
    assignmentMenu.addItem("조회", new AssignViewHandler(assignmentDao));
    assignmentMenu.addItem("변경", new AssignModifyHandler(assignmentDao));
    assignmentMenu.addItem("삭제", new AssignDeleteHandler(assignmentDao));
    assignmentMenu.addItem("목록", new AssignListHandler(assignmentDao));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(txManager, boardDao, attachedFileDao));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao, attachedFileDao));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao));
    boardMenu.addItem("목록", new BoardListHandler(boardDao));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao));
    memberMenu.addItem("목록", new MemberListHandler(memberDao));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(txManager, greetingDao, attachedFileDao));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, attachedFileDao));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao));
    greetingMenu.addItem("목록", new BoardListHandler(greetingDao));

    mainMenu.addItem("도움말", new HelpHandler());
    mainMenu.addItem("About", new AboutHandler());
  }

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

