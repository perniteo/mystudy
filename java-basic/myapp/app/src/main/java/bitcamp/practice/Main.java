package bitcamp.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int cookingTime = Integer.parseInt(br.readLine());
    StringBuilder str = new StringBuilder();
    while (st.hasMoreTokens()) {
      str.append(st.nextToken());
    }
    int now = Integer.parseInt(str.toString());
    int hour;
    int minute;
    if (cookingTime >= 60) {
      hour = cookingTime / 60;
      minute = cookingTime % 60;
    } else {
      hour = 0;
      minute = cookingTime;
    }
    now += hour * 100 + minute;
    System.out.println(now);
  }
}
