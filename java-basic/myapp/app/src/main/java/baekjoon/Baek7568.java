package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek7568 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] info = new int[N][2];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      info[i][0] = Integer.parseInt(st.nextToken());
      info[i][1] = Integer.parseInt(st.nextToken());
    }
    StringBuilder rank = new StringBuilder();

    for (int i = 0; i < N; i++) {
      int point = 1;
      for (int j = 0; j < N; j++) {
        if (info[i][0] < info[j][0] & info[i][1] < info[j][1]) {
          point++;
        }
      }
      rank.append(point).append(" ");
    }
    System.out.print(rank);
  }
}
