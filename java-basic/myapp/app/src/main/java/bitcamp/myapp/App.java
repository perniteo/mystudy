package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
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
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(System.in);
//    new MainMenu(prompt).execute();
//    prompt.close();

    ObjectRepository<Board> boardRepository = new ObjectRepository<>();
    ObjectRepository<Assignment> assignmentRepository = new ObjectRepository<>();
    ObjectRepository<Member> memberRepository = new ObjectRepository<>();
    ObjectRepository<Board> greetingRepository = new ObjectRepository<>();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup assignmentMenu = new MenuGroup("과제");
    mainMenu.add(assignmentMenu);
    assignmentMenu.add(new MenuItem("등록", new AssignAddHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("조회", new AssignViewHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("변경", new AssignModifyHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("삭제", new AssignDeleteHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("목록", new AssignListHandler(assignmentRepository)));

    MenuGroup boardMenu = new MenuGroup("게시글");
    mainMenu.add(boardMenu);
    boardMenu.add(new MenuItem("등록", new BoardAddHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("조회", new BoardViewHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("변경", new BoardModifyHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("목록", new BoardListHandler(boardRepository)));

    MenuGroup memberMenu = new MenuGroup("회원");
    mainMenu.add(memberMenu);
    memberMenu.add(new MenuItem("등록", new MemberAddHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("조회", new MemberViewHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("변경", new MemberModifyHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("삭제", new MemberDeleteHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("목록", new MemberListHandler(memberRepository)));

    MenuGroup greetingMenu = new MenuGroup("가입인사");
    mainMenu.add(greetingMenu);
    greetingMenu.add(new MenuItem("등록"));
    greetingMenu.add(new MenuItem("조회"));
    greetingMenu.add(new MenuItem("변경"));
    greetingMenu.add(new MenuItem("삭제"));
    greetingMenu.add(new MenuItem("목록"));

    MenuGroup helpMenu = new MenuGroup("도움말");
    mainMenu.add(helpMenu);

    mainMenu.execute(prompt);
    prompt.close();

//    int[] intArray = new int[5];

//    // 배열의 각 요소에 값 할당
//    for (int i = 0; i < intArray.length; i++) {
//      intArray[i] = i * 10;
//    }
//
//    // 배열의 각 요소의 값과 주소를 출력
//    for (int i = 0; i < intArray.length; i++) {
//      System.out.println(
//          "Element " + i + ": Value=" + intArray[i] + ", Address=" + System.identityHashCode(
//              intArray[i]));
  }

}

