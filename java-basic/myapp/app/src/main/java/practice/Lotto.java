package practice;

import java.util.ArrayList;
import java.util.Random;

public class Lotto {

  public static void generateLotto() {

    ArrayList<Integer> balls = new ArrayList<>();
    ArrayList<Integer> win = new ArrayList<>();
    Random random = new Random();

    for (int i = 1; i < 46; i++) {
      balls.add(i);
    }
    while (win.size() < 6) {
      int randomIdx = random.nextInt(balls.size());
      win.add(balls.remove(randomIdx));
    }
    System.out.println(win);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      generateLotto();
    }
  }
}
