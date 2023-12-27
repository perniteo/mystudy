package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringRepeat2675 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    StringTokenizer st = new StringTokenizer(br.readLine());
//    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      StringBuilder sb = new StringBuilder();
      StringTokenizer st = new StringTokenizer(br.readLine());
      int R = Integer.parseInt(st.nextToken());
      String S = st.nextToken();
      for (int j = 0; j < S.length(); j++) {
//        for (int k = 0; k < R; k++) {
//          sb.append(S.charAt(j));
//        }
        sb.append(String.valueOf(S.charAt(j)).repeat(Math.max(0, R)));
      }
      System.out.println(sb);
    }
  }
}
