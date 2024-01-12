package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prime2581 {

  public static void main(String[] args) throws Exception {
    int sumNum = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int M = Integer.parseInt(br.readLine());
    int N = Integer.parseInt(br.readLine());
    int minPrime = N;

    for (; M <= N; M++) {
      if (isPrime(M)) {
        sumNum += M;
        if (minPrime >= M) {
          minPrime = M;
        }
      }
    }
    if (sumNum == 0) {
      System.out.println(-1);
    } else {
      System.out.printf("%d\n%d", sumNum, minPrime);
    }
  }

  public static boolean isPrime(int n) {
    if (n == 1) {
      return false;
    }
    for (int i = 2; i <= (int) Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
