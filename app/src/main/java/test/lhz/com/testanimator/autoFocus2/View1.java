package test.lhz.com.testanimator.autoFocus2;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/21
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class View1 extends LinearLayout {
    public View1(Context context) {
        super(context);
        init();
    }

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setFocusable(true);
////        setFocusableInTouchMode(true);
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                requestFocus();
//            }
//        },2000);
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.e("lhz", "View.getTag():" + getTag() + " ," + "View1---requestFocus");
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz", getTag() + ":" + "View1---dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("lhz", getTag() + " :" +"View1---onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("lhz", getTag() + ":" +"View1---onKeyUp");
        return super.onKeyUp(keyCode, event);
    }
}
