package jni.lhz.com.looplayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/11/19
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class VerticalScrollView extends FrameLayout {

    private final int max = 5;

    private ViewGroup main;

    private Runnable runnable;

    private int curPos = 0;

    private final String[] data = new String[]{"data1", "data2", "data3", "data4", "data5"};

    public VerticalScrollView(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        fillData();

        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();

    }

    private void init() {
        setVerticalScrollBarEnabled(false);
        LayoutInflater.from(getContext()).inflate(R.layout.vertical_scroll_view, this);
        main = findViewById(R.id.main);


        runnable = new Runnable() {
            @Override
            public void run() {
                forwardPage();
            }
        };
    }

    private void fillData() {
        String curText = data[curPos];
//        String preText = curPos == 0 ? data[data.length - 1] : data[curPos - 1];
//        String nextText = curPos == data.length - 1 ? data[0] : data[curPos + 1];
//        TextView behind = (TextView) main.getChildAt(0);
        TextView front = (TextView) main.getChildAt(1);
        front.setText(curText);
//        behind.setText(preText);
    }

    /**
     * 向前翻页
     */
    private void forwardPage(){
        String nextText = curPos == data.length - 1 ? data[0] : data[curPos + 1];
        final TextView behind = (TextView) main.getChildAt(0);
        behind.setText(nextText);

        TextView front = (TextView) main.getChildAt(1);
        AnimationUtils.doForwardAnimation(front, behind, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                behind.bringToFront();
                autoIncrease();
                postDelayed(runnable, 3000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private synchronized void autoIncrease() {
        curPos++;
        curPos = curPos % max;
    }

    private synchronized void autoDescend() {
        curPos--;
        if (curPos < 0) {
            curPos = max - 1;
        }
    }

    private void start() {
        removeCallbacks(runnable);
        postDelayed(runnable, 3000);
    }

    private void stop() {
        removeCallbacks(runnable);
    }


}
