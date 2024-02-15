package practice;

import java.util.Scanner;

public class Bank {

  static Scanner scanner = new Scanner(System.in);

  static Account[] accArr = new Account[100];

  static void showMenu() {
    System.out.print("""
        ---------------------------------------------------
        1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4.출금 | 5. 종료
        ---------------------------------------------------
        """);
  }

  static void selectMessage() {
    System.out.print("선택> ");
  }

  static void printAddress() {
    System.out.print("계좌번호 : ");
  }

  static void printOwner() {
    System.out.print("계좌주 : ");
  }

  static void printInit() {
    System.out.print("초기입금액 : ");
  }

  static void createAccount() {
    System.out.println("""
        ------------
        계좌생성
        ------------""");
    printAddress();
    String address = scanner.next();
    printOwner();
    String owner = scanner.next();
    printInit();
    int init = scanner.nextInt();
    Account account = new Account(address, owner, init);
    for (int i = 0; i < accArr.length; i++) {
      if (accArr[i] == null) {
        accArr[i] = account;
        System.out.println("결과: 계좌가 생성되었습니다.");
        break;
      }
    }
  }

  static void getAddress() {
    System.out.println("""
        -----------------
        계좌목록
        -----------------""");
    for (Account account : accArr) {
      if (account != null) {
        System.out.println(account);
      }
    }
  }

  static void deposit() {
    printAddress();
    String address = scanner.next();
    System.out.print("예금액 : ");
    int balance = scanner.nextInt();
    for (Account account : accArr) {
      if (account != null && account.getAddress().equals(address)) {
        account.setInit(account.getInit() + balance);
      }
    }
    System.out.println("결과: 예금이 성공되었습니다.");
  }

  static void withdraw() {
    printAddress();
    String address = scanner.next();
    System.out.print("출금액 : ");
    int balance = scanner.nextInt();
    for (Account account : accArr) {
      if (account != null && account.getAddress().equals(address)) {
        account.setInit(account.getInit() - balance);
      }
    }
    System.out.println("결과: 출금이 성공되었습니다.");
  }

  public static void main(String[] args) {
    boolean run = true;
//    System.out.print("""
//        ---------------------------------------------------
//        1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4.출금 | 5. 종료
//        ---------------------------------------------------
//        """);
    while (run) {
      showMenu();
      selectMessage();
      int choice = scanner.nextInt();
      if (choice == 1) {
        createAccount();
      } else if (choice == 2) {
        getAddress();
      } else if (choice == 3) {
        deposit();
      } else if (choice == 4) {
        withdraw();
      } else if (choice == 5) {
        System.out.println("프로그램 종료");
        run = false;
      }
    }
  }

  static class Account {

    private String address;
    private String owner;
    private int init;

    Account(String address, String owner, int init) {
      this.address = address;
      this.owner = owner;
      this.init = init;
    }

    //객체(object)를 출력할 때 기본적으로 toString메서드를 호출하기 때문에
    //toString메서드를 오버라이딩하여 명시적으로 출력함
    //Account 클래스에서 생성된 객체를 출력할 때 default값을 정하는 것임
    //다른 출력 method를 생성하고 override하지 않고 사용해도 됨
    @Override
    public String toString() {
      return "계좌번호: " + address + "\s\s계좌주: " + owner + "\s\s잔액: " + init;
    }

    String getAddress() {
      return address;
    }

    void setAddress(String address) {
      this.address = address;
    }

    String getOwner() {
      return owner;
    }

    void setOwner(String owner) {
      this.owner = owner;
    }

    int getInit() {
      return init;
    }

    void setInit(int init) {
      this.init = init;
    }
  }

}
