package test.lhz.com.testjava.java;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/8/27
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class TestExtend {

    public static void main(String[] args){
        Parent t = new Child();
        t.ttt();
    }

    public static class Parent {
        protected void ttt(){
            System.out.print("Parent.ttt()");
        }
    }

    public static class Child extends Parent {
        @Override
        protected void ttt() {
            System.out.print("Child.ttt()");
        }
    }
}
