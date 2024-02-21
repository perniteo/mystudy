package practice;

import java.awt.Toolkit;

public class PracThread {

  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      for (int i = 0; i < 5; i++) {
        toolkit.beep();
        try {
          Thread.sleep(5000);
        } catch (Exception e) {
        }
      }
    });
    thread.start();

    for (int i = 0; i < 5; i++) {
      System.out.println("beep!");
      try {
        Thread.sleep(5000);
      } catch (Exception e) {
      }
    }
  }
}