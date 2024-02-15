package practice.util;

public class WorkerThread extends Thread {

  CustomThreadPool customThreadPool;
  Worker worker;

  public WorkerThread(CustomThreadPool customThreadPool) {
    this.customThreadPool = customThreadPool;
  }

  synchronized public void setWorker(Worker worker) {
    this.worker = worker;
    this.notify();
  }

  @Override
  public void run() {
    try {
      while (true) {
        synchronized (this) {
          this.wait();
        }
        try {
          worker.play();
        } catch (Exception e) {
          System.out.println("Exception");
        }
        customThreadPool.revert(this);
      }
    } catch (Exception e) {
      System.out.println("Exception");
    }
  }
}
