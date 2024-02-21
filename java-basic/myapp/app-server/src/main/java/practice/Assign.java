package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Assign {

  static int now = 0;
  static int as = -1;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static String cur = "메인/과제> ";

  static void execute() throws Exception {
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
        return;
      } else {
        System.out.printf("과제 %s입니다.\n%s", asArr[as], cur);
      }
    }

  }
}
