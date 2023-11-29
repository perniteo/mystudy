import java.util.*;
import java.io.*;

public class Hello {
    public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      for (int i = 0; i < c; i++) {
        a = a ^ b;
      }
      System.out.println(a);
      // Scanner sc = new Scanner(System.in);
      // int a = sc.nextInt();
      // int b = sc.nextInt();
      // int c = sc.nextInt();
      // for (int i = 0; i < c; i++) {
      //   a = a ^ b;
      // }
      // System.out.println(a);
      // Scanner sc = new Scanner(System.in);
      // int a = sc.nextInt();
      // int b = sc.nextInt();

      // if (a < 1024) {
      //   System.out.println("No thanks");
      // }
      // else {
      //   int need = a - 1023;
      //   if ((need & b) == need) {
      //     System.out.println("Thanks");
      //   } else {
      //     System.out.println("Impossible");
      //   }
      // }

  //     System.out.println("Hello, World!"); 
  //     System.out.println("안녕하세용가리 !");
  //     int intValue = 103029770;
  //     byte byteValue = (byte) intValue;
  //     System.out.println(intValue);
  //     System.out.println(byteValue);
  //     double x = 1;
  //     byte y = 2;
  //     System.out.println(x/y);
  //     System.out.println((char)65);
  //     char charValue = 'A';
  //     // short shortValue = charValue;

  //   Scanner sc = new Scanner(System.in);
  //  boolean run = true;
  //  int students = 0;
  //  int[] scores = null;
  //  while (run) {
  //    System.out.print("""
  //        ------------------------------------------------------
  //        1. 학생수 | 2. 점수입력 | 3. 점수리스트 | 4. 분석 | 5. 종료
  //        ------------------------------------------------------
  //        선택 > """);
  //    int inputInt = sc.nextInt();
  //    if (inputInt == 1) {
  //      System.out.print("학생 수 > ");
  //      students = sc.nextInt();
  //      scores = new int[students];
  //    } else if (inputInt == 2) {
  //      for (int i = 0; i < students; i++) {
  //        System.out.printf("scores[%d] > ", i);
  //        scores[i] = sc.nextInt();
  //      }
  //    } else if (inputInt == 3) {
  //      for (int i = 0; i < students; i++) {
  //        System.out.printf("scores[%d]: ", i);
  //        System.out.println(scores[i]);
  //      }
  //    } else if (inputInt == 5) {
  //      System.out.println("프로그램 종료");
  //      run = false;
  //    }
  //  }
    }
}