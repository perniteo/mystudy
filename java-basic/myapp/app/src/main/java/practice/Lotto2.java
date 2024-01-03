package practice;

import java.util.ArrayList;
import java.util.Random;

public class Lotto2 {

  public static void generateLotto() {

    ArrayList<Integer> nums = new ArrayList<>();
    ArrayList<Integer> win = new ArrayList<>();
    Random random = new Random();

    for (int i = 1_000_000; i < 6_000_000; i++) {
      nums.add(i);
    }
    while (win.size() < 5) {
      int randomIdx = random.nextInt(nums.size());
      int num = nums.remove(randomIdx);
      win.add(num);
    }
    System.out.println(win);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      generateLotto();
    }
  }

}
