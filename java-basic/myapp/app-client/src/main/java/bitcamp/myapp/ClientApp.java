package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.network.AssignmentDaoImpl;
import bitcamp.myapp.dao.network.BoardDaoImpl;
import bitcamp.myapp.dao.network.MemberDaoImpl;
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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientApp {

  Prompt prompt = new Prompt(System.in);

  BoardDao boardDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  BoardDao greetingDao;
  MenuGroup mainMenu;

  Socket socket;
  DataInputStream in;
  DataOutputStream out;

  ClientApp() throws Exception {
    prepareNetwork();
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
    new ClientApp().run();
  }

  void prepareNetwork() {
    try {
      System.out.println("loading");
      socket = new Socket("localhost", 7777);
      System.out.println("Success");

      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      boardDao = new BoardDaoImpl("board", in, out);
      assignmentDao = new AssignmentDaoImpl("assignment", in, out);
      memberDao = new MemberDaoImpl("member", in, out);
      greetingDao = new BoardDaoImpl("greeting", in, out);

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
        close();
        break;
      } catch (Exception e) {
        System.err.println("Exception !");
      }
    }
  }

  void close() {
    try (Socket socket = this.socket;
        DataInputStream in = this.in;
        DataOutputStream out = this.out) {
      out.writeUTF("quit");
      System.out.println(in.readUTF());

    } catch (IOException e) {
      System.out.println();
    }
  }

}

