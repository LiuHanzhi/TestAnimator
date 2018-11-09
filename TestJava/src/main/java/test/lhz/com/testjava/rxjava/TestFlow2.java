package test.lhz.com.testjava.rxjava;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/7/17
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class TestFlow2 {

    public static void main(String[] args) {
        Observable.create(emitter -> {
            String s = "test";
            System.out.println("emit:" + s);
            emitter.next(s);
        }).subscribe(o -> System.out.println("receive:" + o));
    }

    public abstract static class Observable<T> {

        static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
            return new ObservableCreate<>(source);
        }

        void subscribe(Observer<T> observer) {
            scheduleAction(observer);
        }

        public abstract void scheduleAction(Observer<T> observer);

    }

    public static final class ObservableCreate<T> extends Observable<T> {
        final ObservableOnSubscribe<T> source;

        ObservableCreate(ObservableOnSubscribe<T> source) {
            this.source = source;
        }

        @Override
        public void scheduleAction(Observer<T> observer) {
            source.subscribe(observer::accept);
        }
    }

    public interface ObservableOnSubscribe<T> {

        void subscribe(Emitter<T> emitter);
    }

    public interface Observer<T> {
        void accept(T t);
    }

    public interface Emitter<T> {
        void next(T t);
    }

}
