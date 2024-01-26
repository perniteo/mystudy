package practice.util;

import java.util.LinkedList;

public class CustomThreadPool implements Pooling<WorkerThread> {

  LinkedList<WorkerThread> list = new LinkedList<>();

  public CustomThreadPool() {
  }

  public CustomThreadPool(int initSize) {
    for (int i = 0; i < initSize; i++) {
      WorkerThread thread = create();
      thread.setName("thread" + (i + 1));
      System.out.println(thread.getName());
    }
  }

  public WorkerThread create() {
    WorkerThread thread = new WorkerThread(this);
    thread.start();
    return thread;
  }

  public WorkerThread get() {
    if (!list.isEmpty()) {
      return list.poll();
    }
    WorkerThread thread = new WorkerThread(this);
    thread.start();
    return thread;
  }

  public void revert(WorkerThread thread) {
    list.add(thread);
  }
}
