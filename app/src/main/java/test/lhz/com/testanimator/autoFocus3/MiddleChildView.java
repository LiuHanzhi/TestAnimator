package test.lhz.com.testanimator.autoFocus3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/12/6
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MiddleChildView extends ChildFocusableLinearLayout implements ChildFocusableLinearLayout.OnFocusChangeListener {

    private MiddleView.ChildLastFocusView childLastFocusView;

    public MiddleChildView(Context context) {
        super(context);
        init();
    }

    public MiddleChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MiddleChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setChildLastFocusView(MiddleView.ChildLastFocusView childLastFocusView) {
        this.childLastFocusView = childLastFocusView;
    }

    private void init() {
        setFocusChangeListener(this);
    }

    @Override
    public boolean onMyFocusChange(View view, boolean hasFocus) {
        return false;
    }

    @Override
    public void onChildFocusChange(View view, boolean hasFocus) {
        if (hasFocus && childLastFocusView != null) {
            childLastFocusView.onChildFocused(view);
        }
    }

}
