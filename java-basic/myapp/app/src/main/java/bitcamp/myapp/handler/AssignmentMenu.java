//package bitcamp.myapp.handler;
//
//import bitcamp.menu.Menu;
//import bitcamp.myapp.vo.Assignment;
//import bitcamp.util.Prompt;
//
//public class AssignmentMenu implements Menu {
//
//  String title;
//  Assignment[] assignments = new Assignment[3];
//  int length = 0;
//  Prompt prompt;
//
//  AssignmentMenu(String title, Prompt prompt) {
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
//  @Override
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
//          break;
//        default:
//          System.out.println("메뉴 번호가 옳지 않습니다!");
//      }
//    }
//  }
//
//  void add() throws Exception {
//    System.out.printf("%s 등록:\n", this.title);
//
//    if (this.length == this.assignments.length) {
////      System.out.println("과제를 더 이상 등록할 수 없습니다.");
//      int oldSize = this.assignments.length;
//      int newSize = oldSize + (oldSize / 2);
//      Assignment[] arr = new Assignment[newSize];
//      System.arraycopy(this.assignments, 0, arr, 0, oldSize);
////      for (int i = 0; i < oldSize; i++) {
////        arr[i] = assignments[i];
////      }
//      this.assignments = arr;
//    }
//
//    Assignment assignment = new Assignment();
//    assignment.title = this.prompt.input("%s명? ", this.title);
//    assignment.content = this.prompt.input("내용? ");
//    assignment.deadline = this.prompt.input("제출 마감일? ");
//
//    assignments[length++] = assignment;
//
//  }
//
//  void view() throws Exception {
//    System.out.printf("%s 조회:\n", this.title);
//    int index = this.prompt.inputInt("번호? ");
//    if (index < 0 || index >= this.length) {
//      System.out.printf("%s 번호가 유효하지 않습니다.\n", this.title);
//      return;
//    }
//    Assignment assignment = this.assignments[index];
//    System.out.printf("%s명: %s\n", this.title, assignment.title);
//    System.out.printf("내용: %s\n", assignment.content);
//    System.out.printf("제출 마감일: %s\n", assignment.deadline);
//
//  }
//
//
//  void modify() throws Exception {
//    System.out.printf("%s 변경:\n", this.title);
//    int index = Integer.parseInt(this.prompt.input("번호? "));
//    if (index < 0 || index >= length) {
//      System.out.printf("%s 번호가 유효하지 않습니다.\n", this.title);
//      return;
//    }
//    Assignment assignment = assignments[index];
//    assignment.title = this.prompt.input("%s명(%s)? ", this.title, assignment.title);
//    assignment.content = this.prompt.input("내용(%s)? ", assignment.content);
//    assignment.deadline = this.prompt.input("제출 마감일(%s)? ", assignment.deadline);
//
//  }
//
//  void delete() throws Exception {
//    System.out.printf("%s 삭제\n", this.title);
//
//    int index = Integer.parseInt(this.prompt.input("번호? "));
//    if (index < 0 || index >= this.length) {
//      System.out.printf("%s 번호가 유효하지 않습니다.\n", this.title);
//    }
//    for (int i = index; i < (this.length - 1); i++) {
//      this.assignments[i] = this.assignments[i + 1];
//    }
//    this.assignments[--this.length] = null;
////    assignment.title = "";
////    assignment.content = "";
////    assignment.deadline = "";
//  }
//
//  void list() {
//    System.out.printf("%s 목록:\n", this.title);
//    System.out.printf("%-18s\t%s\n", this.title, "제출마감일");
//    for (int i = 0; i < this.length; i++) {
//      Assignment assignment = this.assignments[i];
//      System.out.printf("%-20s\t%s\n", assignment.title, assignment.deadline);
////      System.out.printf("%d번째 과제\n", i + 1);
////      System.out.printf("과제명: %s\n", assignment.title);
////      System.out.printf("내용: %s\n", assignment.content);
////      System.out.printf("제출 마감일: %s\n", assignment.deadline);
//    }
//  }
//}