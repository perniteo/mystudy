package practice;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSpeedTest {

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    AtomicInteger atomicInteger = new AtomicInteger();

    Thread t1 = new Thread(() -> {
//      int i1 = 0;
      try {
        for (int i = 0; i < 50000000; i++) {
//          i1 = i;
          atomicInteger.addAndGet(i);
        }
      } finally {
//        System.out.println(i1);
      }
    }
    );
    Thread t2 = new Thread(() -> {
//      int i2 = 0;
      try {
        for (int i = 50000000; i < 100000000; i++) {
//          i2 = i;
          atomicInteger.addAndGet(i);
        }
      } finally {
//        System.out.println(i2);
        System.out.println(atomicInteger);
      }
    }
    );
    try {
      t1.start();
      t2.start();
      t1.join();
      t2.join();
    } finally {
      System.out.println(System.currentTimeMillis() - start);
    }
  }
}
