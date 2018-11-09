package test.lhz.com.testjava.clone;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/10/31
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class ByteArrayTest {

    public static void main(String[] args) {
        test1();
        test2();
        testInteger();
        testString();
    }

    public static void test1() {
        System.out.println("---test1---");
        byte[] a = {'a'};
        byte[] b = a;
        b[0] = 'b';
        System.out.println(a[0]);
        System.out.println(b[0]);
    }

    public static void test2() {
        System.out.println("---test2---");
        byte[] a = {'a'};
        byte[] b = a.clone();
        b[0] = 'b';
        System.out.println(a[0]);
        System.out.println(b[0]);
    }

    /**
     * 基础数据类型int及其对应的类Integer，都是值传递
     */
    public static void testInteger() {
        Integer i = new Integer(3);
        innerTest3(i);
        System.out.println("out integer:" + i);
    }

    private static void innerTest3(Integer i) {
        i = new Integer(4);
        System.out.println("inner integer:" + i);
    }

    /**
     * String类内部是char[]存储结构，原理同Integer，所以也是值传递。
     */
    public static void testString() {
        String i = new String("3");
        innerString(i);
        System.out.println("out String:" + i);
    }

    private static void innerString(String i) {
        i = new String("4");
        System.out.println("inner String:" + i);
    }

}
