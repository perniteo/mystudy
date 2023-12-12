package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardRepository;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(System.in);
//    new MainMenu(prompt).execute();
//    prompt.close();

    BoardRepository boardRepository = new BoardRepository();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup assignmentMenu = new MenuGroup("과제");
    mainMenu.add(assignmentMenu);
    assignmentMenu.add(new MenuItem("등록"));
    assignmentMenu.add(new MenuItem("조회"));
    assignmentMenu.add(new MenuItem("변경"));
    assignmentMenu.add(new MenuItem("삭제"));
    assignmentMenu.add(new MenuItem("목록"));

    MenuGroup boardMenu = new MenuGroup("게시글");
    mainMenu.add(boardMenu);
    boardMenu.add(new MenuItem("등록", new BoardAddHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("조회", new BoardViewHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("변경", new BoardModifyHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("목록", new BoardListHandler(boardRepository, prompt)));

    MenuGroup memberMenu = new MenuGroup("회원");
    mainMenu.add(memberMenu);
    memberMenu.add(new MenuItem("등록"));
    memberMenu.add(new MenuItem("조회"));
    memberMenu.add(new MenuItem("변경"));
    memberMenu.add(new MenuItem("삭제"));
    memberMenu.add(new MenuItem("목록"));

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

