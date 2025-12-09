package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Baek1764 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");

    int n = Integer.parseInt(input[0]);
    int m = Integer.parseInt(input[1]);
    int answer = 0;

    HashSet<String> hs = new HashSet<>();
    ArrayList<String> answer1 = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      String test = br.readLine();
      hs.add(test);
    }

    for (int j = 0; j < m; j++) {
      String test = br.readLine();
      if (hs.contains(test)) {
        answer++;
        answer1.add(test);
      }
    }
    Collections.sort(answer1);
    System.out.println(answer);
    for (int i = 0; i < answer; i++) {
      System.out.println(answer1.get(i));
    }
  }
}
