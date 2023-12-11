//package bitcamp.myapp;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//class Account {
//
//  static final int MIN_BALANCE = 0;
//  static final int MAX_BALANCE = 1_000_000;
//  private int balance;
//
//  public int getBalance() {
//    return balance;
//  }
//
//  public void setBalance(int balance) {
//    if (MIN_BALANCE <= balance && MAX_BALANCE >= balance) {
//      this.balance = balance;
//    }
//  }
//}
//
//class ShopService {
//
//  private static final ShopService singleton = new ShopService();
//
//  private ShopService() {
//  }
//
//  public static ShopService getInstance() {
//    return singleton;
//  }
//}
//
//class Printer {
//
//  static void println(int a) {
//    System.out.println(a);
//  }
//
//  static void println(boolean a) {
//    System.out.println(a);
//  }
//
//  static void println(double a) {
//    System.out.println(a);
//  }
//
//  static void println(String a) {
//    System.out.println(a);
//  }
//}
//
//class Car {
//
//  String color; // 인스턴스 변수
//  String gearType;
//  int door;
//
//  Car() {
//    this("white", "auto", 4); // Car(String color, string gearType, int door)를 호출
//  }
//
//  Car(String color) {
//    this(color, "auto", 4);
//  }
//
//  Car(String color, String gearType, int door) {
//    this.color = color;
//    this.gearType = gearType;
//    this.door = door;
//  }
//}
//
//class Member {
//
//  String name;
//  String id;
//  String password;
//  int age;
//
//  Member(String name, String id) {
//    this.name = name;
//    this.id = id;
//  }
//}
//
//class MemberService {
//
//  boolean login(String id, String password) {
//    return id.equals("hong") && password.equals("12345");
//  }
//
//  void logout(String id) {
//    System.out.println(id + "님이 로그아웃 되었습니다.");
//  }
//}
//
//public class PracticeJava {
//
//  static String company = "MyCompany";
//  static String model = "LCD";
//  static String info = company + "-" + model;
//
//  static {
//    info = company + company;
//  }
//
//  final String nation = "대한민국";
//  final String ssn;
//  int speed;
//
//  public App(String ssn) {
//    this.ssn = ssn;
//  }
//
//  public static void main(String[] args) throws Exception {
//    String str = "S5X!!";
//    StringBuilder sb = new StringBuilder();
//    sb.append("sssss").append("aaaaa");
//    System.out.println(sb.charAt(1));
//    sb.replace(1, 2, "sex");
//    System.out.println(sb);
//    Account account = new Account();
//    account.setBalance(10000);
//    System.out.println(account.getBalance());
//    account.setBalance(-100);
//    System.out.println(account.getBalance());
//    account.setBalance(1000001);
//    System.out.println(account.getBalance());
//    ShopService obj1 = ShopService.getInstance();
//    ShopService obj2 = ShopService.getInstance();
//    if (obj1 == obj2) {
//      System.out.println("같은 ShopService 객체입니다.");
//    } else {
//      System.out.println("다른 ShopSerivce 객체입니다.");
//    }
////    Printer printer = new Printer();
////    printer.println(10);
////    printer.println(true);
////    printer.println(5.7);
////    printer.println("홍길동");
//    Printer.println(10);
//    Printer.println(true);
//    Printer.println(5.7);
//    Printer.println("홍길동");
//    MemberService memberService = new MemberService();
//    boolean result = memberService.login("hong", "12345");
//    if (result) {
//      System.out.println("로그인 되었습니다");
//      memberService.logout("hong");
//    } else {
//      System.out.println("접근이 거부 되었습니다.");
//    }
//    Member user1 = new Member("홍길동", "hong");
//    Car car = new Car();
//    System.out.println(car.color);
//
//    App app = new App("3");
//    App myCar = new App("1");
//    myCar.speed = 30;
//    myCar.run();
//    System.out.println(App.info);
////    Scanner sc = new Scanner(System.in);
////    boolean run = true;
////    int students = 0;
////    int[] scores = null;
////    while (run) {
////      System.out.print("""
////          ------------------------------------------------------
////          1. 학생수 | 2. 점수입력 | 3. 점수리스트 | 4. 분석 | 5. 종료
////          ------------------------------------------------------
////          선택 > """);
////      int inputInt = sc.nextInt();
////      if (inputInt == 1) {
////        System.out.print("학생 수 > ");
////        students = sc.nextInt();
////        scores = new int[students];
////      } else if (inputInt == 2) {
////        for (int i = 0; i < students; i++) {
////          System.out.printf("scores[%d] > ", i);
////          scores[i] = sc.nextInt();
////        }
////      } else if (inputInt == 3) {
////        for (int i = 0; i < students; i++) {
////          System.out.printf("scores[%d]: ", i);
////          System.out.println(scores[i]);
////        }
////      } else if (inputInt == 4) {
////        int best = 0;
////        float sumScores = 0;
////        for (int score : scores) {
////          sumScores += score;
////          if (score > best) {
////            best = score;
////          }
////        }
////        System.out.printf("최고 점수: %d\n", best);
////        System.out.printf("평균 점수: %f\n", sumScores / students);
////      } else if (inputInt == 5) {
////        System.out.println("프로그램 종료");
////        run = false;
////      }
////    }
//
//    System.out.println(197 ^ 197);
//    double[] arr5 = {0.1};
//    System.out.println(arr5[0]);
//    // 주사위 2개를 굴려 합이 5일 때 멈추기
//    while (true) {
//      int dice1 = (int) (Math.random() * 6) + 1;
//      int dice2 = (int) (Math.random() * 6) + 1;
//      System.out.println("(" + dice1 + ", " + dice2 + ")");
//      if (dice1 + dice2 == 5) {
////        System.out.println("(" + dice1 + ", " + dice2 + ")");
//        break;
//      }
//    }
//
////    Integer a = null;
////    int b = (a == null) ? 0 : a.intValue();
////    int result = 0;
////    for (int i = 1; i <= 100; ++i) {
////      if (i % 3 == 0) {
////        result += i;
////      }
////    }
////    System.out.println("1부터 100까지의 정수 중에서 3의 배수의 합 : " + result);
//    String ANSI_CLEAR = "\033[0m";
//    String ANSI_RED = "\033[0;31m";
//    String ANSI_BOLD_RED = "\033[1;31m";
//    String appTitle = "[과제관리 시스템]";
//    /*System.out.println("""
//        \033[1;31m
//        --------------------------------
//        [과제관리 시스템]
//
//        1. 과제
//        2. 게시글
//        3. 도움말
//        4. 종료
//        --------------------------------
//        \033[0m"""
//    );*/
//    String menu1 = "1. 과제", menu2 = "2. 게시글", menu3 = "3. 도움말",
//        menu4 = "4. 종료";
//    System.out.println(ANSI_BOLD_RED + appTitle + ANSI_CLEAR + "\n");
////    System.out.println("1. 과제");
////    System.out.printf("%1$d. 게시글\n", 2);
//    System.out.println(menu1);
//    System.out.println(menu2);
//    System.out.println(menu3);
//    System.out.println(ANSI_RED + menu4 + ANSI_CLEAR);
//
////    String grade = "B";
////
////    int score1 = 0;
////    switch (grade) {
////      case "A" -> score1 = 100;
////      case "B" -> {
////        int result = 100 - 20;
////        score1 = result;
////      }
////      default -> score1 = 60;
////    }
////
////    System.out.println(score1);
//    // 4x + 5y = 60 (단 x와 y는 10이하의 자연수) 일 때,
//    // 2중 for 문으로 모든 해를 구하기
//    for (int x = 0; x <= 10; x++) {
//      for (int y = 0; y <= 10; y++) {
//        if (x * 4 + y * 5 == 60) {
//          System.out.println("(" + x + ", " + y + ")");
//        }
//      }
//    }
//    for (int i = 0; i <= 4; i++) {
//      for (int j = 0; j <= i; j++) {
//        System.out.print("*");
//      }
//      System.out.println();
//    }
//
//  }
//
//  void run() {
//    System.out.println(speed + " 속도로 주행합니다.");
//  }
//
//}
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
//package bitcamp.myapp;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class App {
//
//  static int now = 0;
//  static int as = -1;
//  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//  static String cur = "메인/과제> ";

//  static void assign() throws Exception {
//    System.out.println("""
//        [과제]
//        1. 등록
//        2. 조회
//        3. 변경
//        4. 삭제
//        0. 이전""");
//    System.out.print(cur);
//    while (as != 0) {
//      as = Integer.parseInt(br.readLine());
//      String[] asArr = {"0", "등록", "조회", "변경", "삭제"};
//      if (as == 0) {
//        now = 0;
//        cur = "메인 >";
//        as = -1;
//        break;
//      } else {
//        System.out.printf("과제 %s입니다.\n%s", asArr[as], cur);
//      }
//    }
//
//  }
//
//  static void post() throws Exception {
//    cur = "메인/게시글> ";
//    System.out.println("""
//        [게시글]
//        1. 등록
//        2. 조회
//        3. 변경
//        4. 삭제
//        0. 이전""");
//    System.out.print(cur);
//    while (as != 0) {
//      as = Integer.parseInt(br.readLine());
//      String[] postArr = {"0", "등록", "조회", "변경", "삭제"};
//      if (as == 0) {
//        now = 0;
//        cur = "메인 >";
//        as = -1;
//        break;
//      } else {
//        System.out.printf("게시글 %s입니다.\n%s", postArr[as], cur);
//      }
//    }
//
//  }
//
//  static void help() {
//
//  }
//
//  static void exit() {
//
//  }


/*
 * This Java source file was generated by the Gradle 'init' task.
 */
//package bitcamp.myapp;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//  public class App {
//
//    static int now = 0;
//    static int as = -1;
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static String cur = "메인/과제> ";

//  static void assign() throws Exception {
//    System.out.println("""
//        [과제]
//        1. 등록
//        2. 조회
//        3. 변경
//        4. 삭제
//        0. 이전""");
//    System.out.print(cur);
//    while (as != 0) {
//      as = Integer.parseInt(br.readLine());
//      String[] asArr = {"0", "등록", "조회", "변경", "삭제"};
//      if (as == 0) {
//        now = 0;
//        cur = "메인 >";
//        as = -1;
//        break;
//      } else {
//        System.out.printf("과제 %s입니다.\n%s", asArr[as], cur);
//      }
//    }
//
//  }
//
//  static void post() throws Exception {
//    cur = "메인/게시글> ";
//    System.out.println("""
//        [게시글]
//        1. 등록
//        2. 조회
//        3. 변경
//        4. 삭제
//        0. 이전""");
//    System.out.print(cur);
//    while (as != 0) {
//      as = Integer.parseInt(br.readLine());
//      String[] postArr = {"0", "등록", "조회", "변경", "삭제"};
//      if (as == 0) {
//        now = 0;
//        cur = "메인 >";
//        as = -1;
//        break;
//      } else {
//        System.out.printf("게시글 %s입니다.\n%s", postArr[as], cur);
//      }
//    }
//
//  }
//
//  static void help() {
//
//  }
//
//  static void exit() {
//
//  }

//    public static void main(String[] args) throws Exception {
//      MainMenu.execute();
//
//    }
//  }
//package bitcamp.myapp;

//import java.io.InputStreamReader;
//
//public class MainMenu {
//
//  static String cur = "메인> ";
//  static int now = 0;
//  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//  static void printMenu() {
//    System.out.println("""
//        ---------------------------
//        [과제관리 시스템]
//        1. 과제
//        2. 게시글
//        3. 도움말
//        4. 종료""");
//    System.out.print(cur);
//  }
//
//  static void execute() throws Exception {
//    while (now != 4) {
//      if (now == 0) {
//        printMenu();
//      }
//      now = Integer.parseInt(br.readLine());
//      if (now == 1) {
//        Assign.execute();
//      } else if (now == 2) {
//        Post.execute();
//      }
////      else if (now == 3) {
////        help();
////      } else if (now == 4) {
////        exit();
////      }
//    }
//
//  }
//}
