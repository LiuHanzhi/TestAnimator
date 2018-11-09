package test.lhz.com.testjava.rxjava;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/7/17
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class TestFlow {

    public static void main(String[] args){
        A a= new A();
        a.a1().a2().run();
    }

    public static class A {

        public void run(){
            System.out.println(getClass().getSimpleName()+".run()");
        }

        public A a1(){
           A1 a1 = new A1(this);
           return a1;
        }

        public A a2(){
            A2 a2 = new A2(this);
            return a2;
        }
    }

    public static class A1 extends A {
        private A a;
        public A1(A a){
            this.a = a;
        }
        @Override
        public void run() {
            super.run();
            a.run();
        }
    }

    public static class A2 extends A {
        private A a;
        public A2(A a){
            this.a = a;
        }
        @Override
        public void run() {
            super.run();
            a.run();
        }
    }
}
