package practice;

import java.util.ArrayList;
import java.util.Random;

public class Lotto {

  public static void main(String[] args) {
    ArrayList<Integer> balls = new ArrayList<>();
    for (int i = 1; i < 46; i++) {
      balls.add(i);
    }
    ArrayList<Integer> win = new ArrayList<>();
    Random random = new Random();
//    System.out.println(random.toString());
//    System.out.println(balls);

    while (win.size() < 6) {
      int randomIdx = random.nextInt(balls.size());
      int ball = balls.remove(randomIdx);
      win.add(ball);
    }
    System.out.println(win);
  }
}
