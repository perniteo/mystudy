package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Post {

  static int now = 0;
  static int as = -1;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static String cur = "메인/과제> ";

  static void execute() throws Exception {
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
}
