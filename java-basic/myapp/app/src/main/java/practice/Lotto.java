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
      int ball = balls.remove(randomIdx);
      win.add(ball);
    }
    System.out.println(win);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      generateLotto();
    }
  }
}
