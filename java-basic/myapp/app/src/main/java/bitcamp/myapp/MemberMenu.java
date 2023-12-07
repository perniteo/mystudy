package bitcamp.myapp;

public class MemberMenu {

  static Member[] arr = new Member[3];
  static int length = 0;

  static void printMenu() {
    System.out.println("[회원]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("5. 목록");
    System.out.println("0. 이전");
  }

  static void execute() throws Exception {
    printMenu();

    while (true) {
      String input = Prompt.input("메인/회원> ");

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
        default:
          System.out.println("잘못된 입력입니다.");

      }
    }

  }

  static void add() throws Exception {
    if (length == arr.length) {
      Member[] newArr = new Member[length + (length >> 1)];
      System.arraycopy(arr, 0, newArr, 0, length);
      arr = newArr;
    }
    System.out.println("회원 등록");

    Member member = new Member();

    member.email = Prompt.input("이메일: ");
    member.name = Prompt.input("이름: ");
    member.password = Prompt.input("암호: ");
    member.joinDate = Prompt.input("가입일: ");

    arr[length++] = member;
  }

  static void view() throws Exception {
    System.out.println("회원 정보 조회");
    int index = Integer.parseInt(Prompt.input(String.format("몇 번을 조회?(0~%d) ", length - 1)));
    if (index < 0 || index >= length) {
      System.out.println("잘못된 입력입니다.");
      return;
    }
    Member member = arr[index];
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("암호: %s\n", member.password);
    System.out.printf("가입일: %s\n", member.joinDate);
  }

  static void modify() throws Exception {
    System.out.println("회원 정보 수정");
    int index = Integer.parseInt(Prompt.input("몇 번을 수정?(0~) "));
    if (index < 0 || index >= length) {
      System.out.println("잘못된 입력입니다.");
      return;
    }
    Member member = arr[index];
    member.email = Prompt.input("이메일(%s)? ", member.email);
    member.name = Prompt.input("이름(%s)? ", member.name);
    member.password = Prompt.input("암호(%s)? ", member.password);
    member.joinDate = Prompt.input("가입일(%s)? ", member.joinDate);
  }

  static void delete() throws Exception {
    System.out.println("회원 정보 삭제");
    int index = Integer.parseInt(Prompt.input("몇 번을 삭제?(0~) "));
    if (index < 0 || index >= length) {
      System.out.println("잘못된 입력입니다.");
      return;
    }

    for (int i = index; i < (length - 1); i++) {
      arr[i] = arr[i + 1];
    }
    arr[--length] = null;
  }

  static void list() {
    System.out.println("회원 목록 조회");
    System.out.printf("%-18s\t%s\t%s\t%s\n", "이메일", "이름", "암호", "가입일");

    for (int i = 0; i < length; i++) {
      Member member = arr[i];
      System.out.printf("%-20s\t%s\t%s\t%s\n", member.email, member.name, member.password,
          member.joinDate);
    }
  }
}
