package net.javacogito.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementSynchronize {
  private int value = 0;
  private ReentrantLock lock = new ReentrantLock();
  private static final int NUM_THREADS = 2;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    final IncrementSynchronize is = new IncrementSynchronize();

    // Submit 10 000 tasks. Each increments value by 1. We expect 10 000 as result.
    for (int i = 1; i <= 10_000; i++) {
      executor.submit(new Runnable() {
        @Override public void run() {
          is.getNextValue3(); // <--- replace this method with getNextValue0
                              // getNextValue1 and getNextValue2
                              // to check if the synchronization works.
        }
      });
    }
    executor.shutdown();
    // Wait until all tasks are finished
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
    }
    // Now we ready to print the result.
    System.out.println(is.value);
  }

  /**
   * Not synchronized method
   * @return
   */

  public int getNextValue0() {
    return value++;
  }

  /**
   * Synchronized method
   * @return
   */
  public synchronized int getNextValue1() {
    return value++;
  }


  /**
   * Synchronized block
   * @return
   */
  public int getNextValue2() {
    synchronized (this) {
      return value++;
    }
  }

  /**
   * Synchronization via lock
   * @return
   */
  public int getNextValue3() {
    lock.lock();
    try {
      return value++;
    } finally {
      lock.unlock();
    }
  }
}
