package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek27433 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    System.out.print(factorial(N));
  }

  public static long factorial(int N) {
    if (N == 0 || N == 1) {
      return 1;
    }
    return factorial(N - 1) * N;
  }
}
