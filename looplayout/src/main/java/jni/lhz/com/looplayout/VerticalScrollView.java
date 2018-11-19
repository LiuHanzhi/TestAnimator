package jni.lhz.com.looplayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/11/19
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class VerticalScrollView extends NestedScrollView {

    private final int max = 5;

    private ViewGroup main;

    private TextView mOneTextView;

    private TextView mTwoTextView;

    private TextView mThreeTextView;

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
        scrollBy(0, mOneTextView.getMeasuredHeight());
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
        mOneTextView = findViewById(R.id.one);
        mTwoTextView = findViewById(R.id.two);
        mThreeTextView = findViewById(R.id.three);


        runnable = new Runnable() {
            @Override
            public void run() {
                autoIncrease();
            }
        };
    }

    private void fillData() {
        String curText = data[curPos];
        String preText = curPos == 0 ? data[data.length - 1] : data[curPos - 1];
        String nextText = curPos == data.length - 1 ? data[0] : data[curPos + 1];
        TextView one = (TextView) main.getChildAt(0);
        TextView two = (TextView) main.getChildAt(1);
        TextView three = (TextView) main.getChildAt(2);
        one.setText(preText);
        two.setText(curText);
        three.setText(nextText);
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
