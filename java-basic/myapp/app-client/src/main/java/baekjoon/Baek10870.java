package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek10870 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    System.out.print(fibonacci(n));

  }

  public static int fibonacci(int n) {
    if (n == 0 | n == 1) {
      return n;
    }
    return fibonacci(n - 2) + fibonacci(n - 1);
  }
}
