package test.lhz.com.testanimator.dispatchKeyEvent;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/21
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class View3 extends LinearLayout {
    public View3(Context context) {
        super(context);
        init();
    }

    public View3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setFocusable(true);
//        requestFocus();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.e("lhz","View3.requestFocus");
//        if (getFocusedChild() == null) {
//            Log.e("lhz","getFocusedChild == null");
//            getChildAt(0).requestFocus();
//        }
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        Log.e("lhz","gainFocus="+gainFocus + " hasFocus="+hasFocus()+" direction="+direction);
        if(gainFocus) {
            setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        } else {
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz", "View3---dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("lhz", "View3---onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("lhz", "View3---onKeyUp");
        return super.onKeyUp(keyCode, event);
    }
}
