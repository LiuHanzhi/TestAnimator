package test.lhz.com.testanimator.focus;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 当焦点移动到当前ViewGroup时，让第一个child获得焦点
 *
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/8/20
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MyLinearLayout extends LinearLayout implements View.OnFocusChangeListener {
    public MyLinearLayout(Context context) {
        super(context);
        init();
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        setFocusable(true);
        setFocusableInTouchMode(true);

        post(new Runnable() {
            @Override
            public void run() {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    child.setOnFocusChangeListener(MyLinearLayout.this);
                }
            }
        });

    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.e("lhz", getTag() + ":" + "direction..." + direction);
        Log.e("lhz", getTag() + ":" + "hasFocus():" + hasFocus());
        if (null == getFocusedChild()) {
            //请求默认焦点
            requestDefaultFocus();
        }
        return false;
    }

    private void requestDefaultFocus() {
        Log.e("lhz", getTag() + ":" + "requestDefaultFocus");
        final View view = getChildAt(0);
        if (view != null) {
            if (!hasFocus()) {
                //模拟获取焦点
                setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
            }
            Log.e("lhz", getTag() + ":" + "requestDefaultFocus--first child request focus");
            view.requestFocus();

        }
    }

    @Override
    public void onFocusChange(View v, final boolean hasFocus) {
        Log.e("lhz", getTag() + ":" + "onFocusChange:view--" + v.getTag() + ",hasFocus:" + hasFocus);
        if (!hasFocus) {
            post(new Runnable() {
                @Override
                public void run() {
                    Log.e("lhz", getTag() + ":" + "hasFocus():" + hasFocus());
                    if (!hasFocus()) {
                        //模拟失去焦点
                        onFocusChanged(true, FOCUS_DOWN, null);
                    }
                }
            });
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        Log.e("lhz", "gainFocus=" + gainFocus + " hasFocus=" + hasFocus() + " direction=" + direction);
        if (gainFocus) {
            setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        } else {
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }
}
