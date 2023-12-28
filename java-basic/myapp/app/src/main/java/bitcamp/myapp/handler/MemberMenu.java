//package bitcamp.myapp.handler;
//
//import bitcamp.menu.Menu;
//import bitcamp.myapp.vo.Member;
//import bitcamp.util.Prompt;
//
//public class MemberMenu implements Menu {
//
//  String title;
//  Member[] arr = new Member[3];
//  int length = 0;
//  Prompt prompt;
//
//  MemberMenu(String title, Prompt prompt) {
//    this.title = title;
//    this.prompt = prompt;
//  }
//
//  void printMenu() {
//    System.out.printf("[%s]\n", this.title);
//    System.out.println("1. 등록");
//    System.out.println("2. 조회");
//    System.out.println("3. 변경");
//    System.out.println("4. 삭제");
//    System.out.println("5. 목록");
//    System.out.println("0. 이전");
//  }
//
//  @Override
//  public String getTitle() {
//    return null;
//  }
//
//  public void execute(Prompt prompt) throws Exception {
//    this.printMenu();
//
//    while (true) {
//      String input = this.prompt.input("메인/%s> ", this.title);
//
//      switch (input) {
//        case "1":
//          add();
//          break;
//        case "2":
//          view();
//          break;
//        case "3":
//          modify();
//          break;
//        case "4":
//          delete();
//          break;
//        case "5":
//          list();
//          break;
//        case "0":
//          return;
//        case "menu":
//          printMenu();
//        default:
//          System.out.println("잘못된 입력입니다.");
//
//      }
//    }
//
//  }
//
//  void add() throws Exception {
//    if (this.length == this.arr.length) {
//      Member[] newArr = new Member[this.length + (this.length >> 1)];
//      System.arraycopy(this.arr, 0, newArr, 0, this.length);
//      this.arr = newArr;
//    }
//    System.out.printf("%s 등록\n", this.title);
//
//    Member member = new Member();
//
//    member.email = this.prompt.input("이메일: ");
//    member.name = this.prompt.input("이름: ");
//    member.password = this.prompt.input("암호: ");
//    member.joinDate = this.prompt.input("가입일: ");
//
//    this.arr[this.length++] = member;
//  }
//
//  void view() throws Exception {
//    System.out.printf("%s 정보 조회\n", this.title);
//    int index = Integer.parseInt(this.prompt.input(String.format("몇 번을 조회?(0~%d) ", length - 1)));
//    if (index < 0 || index >= this.length) {
//      System.out.println("잘못된 입력입니다.");
//      return;
//    }
//    Member member = this.arr[index];
//    System.out.printf("이메일: %s\n", member.email);
//    System.out.printf("이름: %s\n", member.name);
//    System.out.printf("암호: %s\n", member.password);
//    System.out.printf("가입일: %s\n", member.joinDate);
//  }
//
//  void modify() throws Exception {
//    System.out.printf("%s 정보 수정\n", this.title);
//    int index = Integer.parseInt(this.prompt.input("몇 번을 수정?(0~) "));
//    if (index < 0 || index >= this.length) {
//      System.out.println("잘못된 입력입니다.");
//      return;
//    }
//    Member member = this.arr[index];
//    member.email = this.prompt.input("이메일(%s)? ", member.email);
//    member.name = this.prompt.input("이름(%s)? ", member.name);
//    member.password = this.prompt.input("암호(%s)? ", member.password);
//    member.joinDate = this.prompt.input("가입일(%s)? ", member.joinDate);
//  }
//
//  void delete() throws Exception {
//    System.out.printf("%s 정보 삭제\n", this.title);
//    int index = Integer.parseInt(this.prompt.input("몇 번을 삭제?(0~) "));
//    if (index < 0 || index >= this.length) {
//      System.out.println("잘못된 입력입니다.");
//      return;
//    }
//
//    for (int i = index; i < (this.length - 1); i++) {
//      this.arr[i] = this.arr[i + 1];
//    }
//    this.arr[--this.length] = null;
//  }
//
//  void list() {
//    System.out.printf("%s 목록 조회\n", this.title);
//    System.out.printf("%-18s\t%s\t%s\t%s\n", "이메일", "이름", "암호", "가입일");
//
//    for (int i = 0; i < this.length; i++) {
//      Member member = this.arr[i];
//      System.out.printf("%-20s\t%s\t%s\t%s\n", member.email, member.name, member.password,
//          member.joinDate);
//    }
//  }
//}
