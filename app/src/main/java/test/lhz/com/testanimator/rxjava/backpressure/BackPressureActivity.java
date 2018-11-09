package test.lhz.com.testanimator.rxjava.backpressure;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/7/12
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class BackPressureActivity extends Activity {

    private static final String TAG = BackPressureActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        testObserveOnAndSubscribeOn2();
    }

    private void testPressure() {
        //被观察者在主线程中，每1ms发送一个事件
        Observable.interval(1, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.newThread())
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG", "---->" + aLong);
                    }
                });
    }


    /**
     * observeOn：设置Observer观察者在什么线程运行；
     * subscribeOn：设置Observable被观察者在什么线程运行；
     * <p>
     * 以上是最基本的使用，但是在使用的时候，调用的顺序和次数都会有影响：
     * subscribeOn：　subscribeOn 作用于该操作符之前的 Observable 的创建操符作以及 doOnSubscribe 操作符
     * observeOn：　observeOn除了设置Observer观察者在什么线程运行，还将影响后面的onNext,map….的运行线程
     */
    private void testObserveOnAndSubscribeOn() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);

            }
        });
        //该类只接收ｎｅｘｔ发出的事件
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        observable
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "a thread is: " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext a: " + integer);
                    }
                })
//
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "c thread is: " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext c: " + integer);
                        return Observable.just(integer);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "b thread is : " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext b: " + integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private void testObserveOnAndSubscribeOn2() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);

            }
        });
        //该类只接收ｎｅｘｔ发出的事件
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        observable
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "a thread is: " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext a: " + integer);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "b thread is : " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext b: " + integer);
                    }
                })
//                .observeOn(AndroidSchedulers.mainThread())

//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        Thread.sleep(10000);
//                        Log.i(TAG, "doOnSubscribe:" + Thread.currentThread().getName());
//                    }
//                })

                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "d thread is : " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext d: " + integer);
                    }
                })

//                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                        Thread.sleep(10000);
                        Log.d(TAG, "c thread is: " + Thread.currentThread().getName());
                        Log.d(TAG, "doOnNext c: " + integer);
                        return Observable.just(integer);
                    }
                })
                .subscribe(consumer);
    }
}
