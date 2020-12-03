package test.lhz.com.testanimator.autoFocus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import test.lhz.com.testanimator.R;


/**
 * 当焦点移动到当前ViewGroup时，让第一个child获得焦点
 *
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/8/20
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class ChildFocusableLinearLayout2 extends LinearLayout implements View.OnFocusChangeListener {

    protected int mSelectedPosition = -1;

    public ChildFocusableLinearLayout2(Context context) {
        super(context);
        init(context, null);
    }

    public ChildFocusableLinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChildFocusableLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setFocusable(true);
        setFocusableInTouchMode(true);

        post(new Runnable() {
            @Override
            public void run() {
                registerChildFocusChangeListener();
            }
        });
    }

    private void registerChildFocusChangeListener() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.isFocusable()) {
                child.setOnFocusChangeListener(ChildFocusableLinearLayout2.this);
            }
        }
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.e("lhz", "View Tag:" + getTag() + "===" + "requestFocus:direction..." + direction);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "requestFocus:hasFocus():" + hasFocus());
        if (null == getFocusedChild()) {
            //请求默认焦点
            requestDefaultFocus(direction);
        }
        return false;
    }


    private void requestDefaultFocus(int direction) {
        if ((direction == FOCUS_DOWN && getOrientation() == VERTICAL) || (direction == FOCUS_RIGHT && getOrientation() == HORIZONTAL)) {//如果焦点是从当前view的上方移动过来，则让第一个可focus的child获取焦点
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                if (view.isFocusable()) {
                    mSelectedPosition = i;
                    break;
                }
            }

        } else if ((direction == FOCUS_UP && getOrientation() == VERTICAL) || (direction == FOCUS_LEFT && getOrientation() == HORIZONTAL)) {//如果焦点是从当前view的下方移动过来，则让最后一个可focus的child获取焦点
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View view = getChildAt(i);
                if (view.isFocusable()) {
                    mSelectedPosition = i;
                    break;
                }
            }
        }

        if (mSelectedPosition < 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                if (view.isFocusable()) {
                    mSelectedPosition = i;
                    break;
                }
            }
        }
        if (mSelectedPosition < 0) {
            return;
        }
        final View view = getChildAt(mSelectedPosition);
        if (view != null) {
            Log.e("lhz", "View Tag:" + getTag() + "===" + "requestDefaultFocus--child index:" + mSelectedPosition);
            view.requestFocus();

        }
    }

    @Override
    public void onFocusChange(View v, final boolean hasFocus) {
        Log.e("lhz", "View Tag:" + getTag() + "===" + "onFocusChange:view--" + v.getTag() + ",isViewFocused:" + hasFocus);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "onFocusChange:hasFocus():" + hasFocus());
//        View view = findFocus();
        if (hasFocus) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (v == child) {
                    mSelectedPosition = i;
                    break;
                }
            }
        }
    }

}
