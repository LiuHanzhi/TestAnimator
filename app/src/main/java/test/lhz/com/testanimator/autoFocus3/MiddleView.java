package test.lhz.com.testanimator.autoFocus3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/12/6
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MiddleView extends ChildFocusableLinearLayout implements ChildFocusableLinearLayout.OnFocusChangeListener {

    private MiddleChildView middleTopChildView;

    private MiddleChildView middleBottomChildView;

    public MiddleView(Context context) {
        super(context);
        init();
    }

    public MiddleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MiddleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.autofocus3_middle_view, this, true);

        middleTopChildView = findViewById(R.id.middle_top);
        middleBottomChildView = findViewById(R.id.middle_bottom);
        middleTopChildView.setChildLastFocusView(childLastFocusView);
        middleBottomChildView.setChildLastFocusView(childLastFocusView);
        setFocusChangeListener(this);
    }

    @Override
    public boolean onMyFocusChange(View view, boolean hasFocus) {
        if (lastFocusView == null) {
            middleTopChildView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    middleTopChildView.requestFocus();
                }
            },10);
        } else {
            if (lastFocusView.getParent() == middleBottomChildView) {
                middleBottomChildView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        middleBottomChildView.requestFocus();
                    }
                },10);

            } else {
                middleTopChildView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        middleTopChildView.requestFocus();
                    }
                },10);
            }
        }
        return true;
    }

    @Override
    public void onChildFocusChange(View view, boolean hasFocus) {

    }

    private View lastFocusView;

    private ChildLastFocusView childLastFocusView = new ChildLastFocusView() {
        @Override
        public void onChildFocused(View view) {
            lastFocusView = view;
        }
    };

    public interface ChildLastFocusView {
        void onChildFocused(View view);
    }

}
