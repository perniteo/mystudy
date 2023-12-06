package bitcamp.myapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prompt {

  static BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

  static String input(String title, Object... args) throws Exception {
    System.out.printf(title, args);
    return keyIn.readLine();
  }

  static void close() throws Exception {
    keyIn.close();
  }
}
