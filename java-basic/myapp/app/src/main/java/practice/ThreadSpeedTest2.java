package practice;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSpeedTest2 {

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    AtomicInteger i1 = new AtomicInteger();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 100000000; i++) {
        i1.addAndGet(i);
//        System.out.println(Thread.currentThread().isInterrupted());
      }
    });
    t1.start();
    t1.join();
    System.out.println(i1);
    System.out.println(System.currentTimeMillis() - start);
  }

}
