package bitcamp.myapp;

public class BoardMenu {

  String title;
  Board[] arr = new Board[3];
  int length = 0;
  BoardMenu(String title) {
    this.title = title;
  }

  void printMenu() {
    System.out.printf("[%s]\n", this.title);
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  void add() throws Exception {
    if (this.length == this.arr.length) {
      Board[] newArr = new Board[this.length + (this.length / 2)];
      System.arraycopy(this.arr, 0, newArr, 0, this.length);
      this.arr = newArr;
    }
    System.out.println("게시글 등록:");

    Board board = new Board();
    board.title = Prompt.input("제목: ");
    board.content = Prompt.input("내용: ");
    board.writer = Prompt.input("작성자: ");
    board.createDate = Prompt.input("작성일: ");
    this.arr[this.length++] = board;
  }

  void view() throws Exception {
    System.out.println("게시글 조회");
    int index = Integer.parseInt(Prompt.input("몇 번을 조회?(0 ~)"));
    if (index < 0 || index >= this.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = this.arr[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createDate);
  }

  void modify() throws Exception {
    System.out.println("게시글 변경");
    int index = Integer.parseInt(Prompt.input("몇 번을 변경?(0 ~"));
    if (index < 0 || index >= this.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Board board = this.arr[index];
    board.title = Prompt.input("제목(%s) :", board.title);
    board.content = Prompt.input("내용(%s) :", board.content);
    board.writer = Prompt.input("작성자(%s) :", board.writer);
    board.createDate = Prompt.input("작성일(%s) :", board.createDate);
  }

  void delete() throws Exception {
    System.out.println("과제 삭제");

    int index = Integer.parseInt(Prompt.input("몇 번을 삭제?(0 ~)"));
    if (index < 0 || index >= this.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    for (int i = index; i < (this.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--length] = null;
  }

  void list() {
    System.out.println("게시글 목록을 호출합니다.");
    System.out.printf("%-18s%s\t%s\n", "제목", "작성자", "작성일");
    for (int i = 0; i < this.length; i++) {
      Board board = this.arr[i];
      System.out.printf("%-20s%s\t\t%s\n", board.title, board.writer, board.createDate);
    }
  }

  void execute() throws Exception {
    this.printMenu();
    while (true) {
      String input = Prompt.input("메인/%s> ", this.title);

      switch (input) {
        case "1":
          this.add();
          break;
        case "2":
          this.view();
          break;
        case "3":
          this.modify();
          break;
        case "4":
          this.delete();
          break;
        case "5":
          this.list();
          break;
        case "0":
          return;
        case "menu":
          this.printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
}
