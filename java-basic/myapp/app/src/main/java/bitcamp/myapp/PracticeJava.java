package bitcamp.myapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PracticeJava {

  static int now = 0;
  static int as = -1;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static String cur = "메인/과제> ";

  static void assign() throws Exception {
    System.out.println("""
        [과제]
        1. 등록
        2. 조회
        3. 변경
        4. 삭제
        0. 이전""");
    System.out.print(cur);
    while (as != 0) {
      as = Integer.parseInt(br.readLine());
      String[] asArr = {"0", "등록", "조회", "변경", "삭제"};
      if (as == 0) {
        now = 0;
        cur = "메인 >";
        as = -1;
        break;
      } else {
        System.out.printf("과제 %s입니다.\n%s", asArr[as], cur);
      }
    }

  }

  static void post() throws Exception {
    cur = "메인/게시글> ";
    System.out.println("""
        [게시글]
        1. 등록
        2. 조회
        3. 변경
        4. 삭제
        0. 이전""");
    System.out.print(cur);
    while (as != 0) {
      as = Integer.parseInt(br.readLine());
      String[] postArr = {"0", "등록", "조회", "변경", "삭제"};
      if (as == 0) {
        now = 0;
        cur = "메인 >";
        as = -1;
        break;
      } else {
        System.out.printf("게시글 %s입니다.\n%s", postArr[as], cur);
      }
    }

  }


  public static void main(String[] args) throws Exception {
    String cur = "메인> ";
    while (now != 4) {
      if (now == 0) {
        System.out.println("""
            ---------------------------
            [과제관리 시스템]
            1. 과제
            2. 게시글
            3. 도움말
            4. 종료""");
        System.out.print(cur);
      }
      now = Integer.parseInt(br.readLine());
      if (now == 1) {
        assign();
      } else if (now == 2) {
        post();
      }
    }

  }
}
