package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
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
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.sql.DriverManager;

public class ClientApp {

  Prompt prompt = new Prompt(System.in);

  BoardDao boardDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  BoardDao greetingDao;
  MenuGroup mainMenu;

  ClientApp() throws Exception {
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
    new ClientApp().run();
  }

  void prepareDatabase() {
    try {
      //Driver Class 에서 static 블록으로 등록함 (mySql jar 파일에 포함 돼 있음)
//      Driver driver = new com.mysql.cj.jdbc.Driver();
//      DriverManager.registerDriver(driver);
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost/studydb", "study", "bitcamp!@#123");

      System.out.println("loading");
//      DaoProxyGenerator daoProxyGenerator = new DaoProxyGenerator("localhost", 7777);
      System.out.println("success");

      boardDao = new BoardDaoImpl(connection, 1);
      assignmentDao = new AssignmentDaoImpl(connection);
      memberDao = new MemberDaoImpl(connection);
      greetingDao = new BoardDaoImpl(connection, 2);

    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
    }

  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignAddHandler(assignmentDao, prompt));
    assignmentMenu.addItem("조회", new AssignViewHandler(assignmentDao, prompt));
    assignmentMenu.addItem("변경", new AssignModifyHandler(assignmentDao, prompt));
    assignmentMenu.addItem("삭제", new AssignDeleteHandler(assignmentDao, prompt));
    assignmentMenu.addItem("목록", new AssignListHandler(assignmentDao, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardDao, prompt));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao, prompt));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao, prompt));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, prompt));
    boardMenu.addItem("목록", new BoardListHandler(boardDao, prompt));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao, prompt));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao, prompt));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao, prompt));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao, prompt));
    memberMenu.addItem("목록", new MemberListHandler(memberDao, prompt));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingDao, prompt));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, prompt));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, prompt));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, prompt));
    greetingMenu.addItem("목록", new BoardListHandler(greetingDao, prompt));

    mainMenu.addItem("도움말", new HelpHandler(prompt));
  }


  void run() throws Exception {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.err.println("Exception !");
      }
    }
  }
}

