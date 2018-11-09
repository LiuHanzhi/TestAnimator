package test.lhz.com.testjava.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/7/2
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa");
        list.stream();
    }

    /**
     * 1. 实现Runnable线程案例
     */
    public static void test() {
        //Before Java 8:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8 ");
            }
        }).start();

        //Java 8 way:
        new Thread(() -> System.out.println("In Java8!")).start();
    }
}
