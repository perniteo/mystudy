package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci {

  public static Integer[][] dp = new Integer[41][2];

  static Integer[] fibonacci(int n) {
    if (dp[n][0] == null || dp[n][1] == null) {
      dp[n][0] = fibonacci(n - 1)[0] + fibonacci(n - 2)[0];
      dp[n][1] = fibonacci(n - 1)[1] + fibonacci(n - 2)[1];
    }
    return dp[n];
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
//    System.out.println(dp[0][0]);
    dp[0][0] = 1;
    dp[0][1] = 0;
    dp[1][0] = 0;
    dp[1][1] = 1;

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < T; i++) {
      int N = Integer.parseInt(br.readLine());
      fibonacci(N);
      sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
    }
    System.out.println(sb);

  }
}
