package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek25501 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      String str = br.readLine();
      System.out.println(inPalindrome(str));
    }
  }

  public static String recursion(String s, int l, int r, int cnt) {
    cnt++;
    if (l >= r) {
      return "1 " + cnt;
    } else if (s.charAt(l) != s.charAt(r)) {
      return "0 " + cnt;
    } else {
      return recursion(s, l + 1, r - 1, cnt);
    }
  }

  public static String inPalindrome(String s) {
    return recursion(s, 0, s.length() - 1, 0);
  }
}
