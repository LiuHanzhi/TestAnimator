package test.lhz.com.testjava.thread;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/7/11
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class TestRunnable {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
//        thread1.start();
//        thread2.start();

        int pro = Runtime.getRuntime().availableProcessors();
        System.out.println("pro:"+pro);
    }

    public static class MyRunnable implements Runnable {

        private int count;

        @Override
        public void run() {
            while (count < 1000) {
                count++;
                System.out.println("thread:" + Thread.currentThread() + " ,count:" + count);
            }
        }
    }
}
