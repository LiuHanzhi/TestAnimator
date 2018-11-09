package test.lhz.com.testjava.java;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Java对对象和基本的数据类型的处理是不一样的。
 * 和C语言一样，当把Java的基本数据类型（如int，char，double等）作为入口参数传给函数体的时候，传入的参数在函数体内部变成了局部变量，这个局部变量是输入参数的一个拷贝，所有的函数体内部的操作都是针对这个拷贝的操作，函数执行结束后，这个局部变量也就完成了它的使命，它影响不到作为输入参数的变量。这种方式的参数传递被称为"值传递"。
 * 而在Java中用对象作为入口参数的传递则缺省为"引用传递"，也就是说仅仅传递了对象的一个"引用"，这个"引用"的概念同C语言中的指针引用是一样的。当函数体内部对输入变量改变时，实质上就是在对这个对象的直接操作。
 *
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/9/3
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class TestReference {

    public static void main(String[] args) {
        A a = new A();
        a.setI(1);
        A a1 = a;
        System.out.println(a == a1);
        a = null;
        System.out.println("a:" + a);
        System.out.println("a1:" + a1);


        B b1 = B.TYPE1;
        B b2 = B.TYPE1;
        System.out.println("b1==b2:" + (b1 == b2));

        int b = 10;
        int c = b;
        System.out.println(b == c);

    }


    public static class A {

        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return String.valueOf(i);
        }

    }

    public static enum B {
        TYPE1, TYPE2, TPYE3
    }

}

