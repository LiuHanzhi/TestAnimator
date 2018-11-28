package test.lhz.com.testanimator.dispatchKeyEvent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/11/28
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MyItemView extends LinearLayout implements View.OnFocusChangeListener {

    private LinearLayout container;

    public MyItemView(Context context) {
        super(context);
        init();
    }

    public MyItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
//
//    @Override
//    protected void onMyFocusChanged(View view, boolean hasFocus) {
////        super.onMyFocusChanged(view, hasFocus);
//        Log.e("lhz", "onMyFocusChanged View:" + getTag() + " ,hasFocus:" + hasFocus);
//        container.setVisibility(hasFocus ? VISIBLE : GONE);
//    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_dispatch2_item, this, true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        container = findViewById(R.id.container);

        for (int i = 0; i < container.getChildCount(); i++) {
            container.getChildAt(i).setOnFocusChangeListener(this);
        }
    }

    public void setFocusStyle(boolean hasFocus) {
        Log.e("lhz", "onMyFocusChanged View:" + getTag() + " ,hasFocus:" + hasFocus);
        if (hasFocus || container.getFocusedChild() != null) {
            if (focusedView != null) {
                focusedView.requestFocus();
            } else {
                container.getChildAt(0).requestFocus();

            }
        }
    }


    private View focusedView;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.e("lhz", "child view onFocusChange:" + getTag() + " ,has focus:" + hasFocus + " ,getFocusChild:" + container.getFocusedChild());
        if (hasFocus) {
            container.setVisibility(VISIBLE);
        } else {
            boolean hasFocusInContainer = hasFocusInContainer();
            Log.e("lhz", "hasFocusInContainer:" + hasFocusInContainer);
            if (!hasFocusInContainer) {
                focusedView = container.getFocusedChild();
                container.setVisibility(GONE);
            } else {
                container.setVisibility(VISIBLE);
            }
        }
    }

    private boolean hasFocusInContainer() {
        for (int i = 0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);
            if (child.isFocused()) {
                return true;
            }
        }
        return false;
    }

}
