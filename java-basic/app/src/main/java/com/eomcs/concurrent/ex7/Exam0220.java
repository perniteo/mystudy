// Executors 태스크 프레임워크 - 스레드풀 만들기 : 가변크기 스레드풀
package concurrent.ex7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Exam0220 {

  static class MyRunnable implements Runnable {
    int millisec;

    public MyRunnable(int millisec) {
      this.millisec = millisec;
    }

    @Override
    public void run() {
      try {
        System.out.printf("%s 스레드에서 작업 실행...\n", Thread.currentThread().getName());

        Thread.sleep(millisec);

        System.out.printf("%s 작업 끝내고 스레드 대기중!\n", Thread.currentThread().getName());
      } catch (Exception e) {
        System.out.printf("%s 스레드 실행 중 오류 발생!\n", Thread.currentThread().getName());
      }
    }
  }

  public static void main(String[] args) throws Exception {

    // 스레드의 수를 고정하지 않고 필요할 때마다 스레드를 생성하는 스레드풀이다.
    // 물론 작업을 끝낸 스레드는 다시 사용할 수 있도록 pool에 보관한다.
    ExecutorService executorService = Executors.newCachedThreadPool();

    // 놀고 있는 스레드가 없으면 새 스레드를 생성한다.
    //
    executorService.execute(new MyRunnable(6000));
    System.out.println(getCount(executorService));
    executorService.execute(new MyRunnable(2000));
    System.out.println(getCount(executorService));
    executorService.execute(new MyRunnable(9000));
    System.out.println(getCount(executorService));
    executorService.execute(new MyRunnable(1000));
    System.out.println(getCount(executorService));

    // 작업을 끝낸 스레드가 생길 때까지 일부러 기다린다.
    //
    Thread.sleep(3000);

    // 그러면 새 스레드를 생성하지 않고
    // 작업을 끝낸 스레드가 요청한 작업을 처리한다.
    //
    executorService.execute(new MyRunnable(4000));
    System.out.println(getCount(executorService));

    System.out.println("main() 종료!");
    executorService.shutdown();
  }

  public static int getCount(ExecutorService executorService) {
    return ((ThreadPoolExecutor) executorService).getActiveCount();
  }

}
