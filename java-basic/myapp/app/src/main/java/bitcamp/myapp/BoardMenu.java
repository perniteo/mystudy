package bitcamp.myapp;

public class BoardMenu {

  static Board[] arr = new Board[3];
  static int length = 0;

  static void printMenu() {
    System.out.println("[게시글]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  static void add() {
    if (length == arr.length) {
      Board[] newArr = new Board[length + (length / 2)];
      System.arraycopy(arr, 0, newArr, 0, length);
      arr = newArr;
    }
    System.out.println("게시글 등록:");

    Board board = new Board();
    board.title = Prompt.input("제목: ");
    board.content = Prompt.input("내용: ");
    board.writer = Prompt.input("작성자: ");
    board.createDate = Prompt.input("작성일: ");
    arr[length++] = board;
  }

  static void view() {
    System.out.println("게시글 조회");
    int index = Integer.parseInt(Prompt.input("몇 번을 조회?(0 ~)"));
    if (index < 0 || index >= length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = arr[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createDate);
  }

  static void modify() {
    System.out.println("게시글 변경");
    int index = Integer.parseInt(Prompt.input("몇 번을 변경?(0 ~"));
    if (index < 0 || index >= length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = arr[index];
    board.title = Prompt.input(String.format("제목(%s) :", board.title));
    board.content = Prompt.input(String.format("내용(%s) :", board.content));
    board.writer = Prompt.input(String.format("작성자(%s) :", board.writer));
    board.createDate = Prompt.input(String.format("작성일(%s) :", board.createDate));
  }

  static void delete() {
    System.out.println("과제 삭제");

    int index = Integer.parseInt(Prompt.input("몇 번을 삭제?(0 ~)"));
    if (index < 0 || index >= length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    for (int i = index; i < (length - 1); i++) {
      arr[i] = arr[i + 1];
    }
    arr[--length] = null;
  }

  static void list() {
    System.out.println("게시글 목록을 호출합니다.");
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    for (int i = 0; i < length; i++) {
      Board board = arr[i];
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

  static void execute() {
    printMenu();
    while (true) {
      String input = Prompt.input("메인/게시글> ");

      switch (input) {
        case "1":
          add();
          break;
        case "2":
          view();
          break;
        case "3":
          modify();
          break;
        case "4":
          delete();
          break;
        case "5":
          list();
          break;
        case "0":
          return;
        case "menu":
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
}
