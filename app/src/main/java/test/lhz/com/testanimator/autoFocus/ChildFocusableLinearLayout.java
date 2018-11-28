package test.lhz.com.testanimator.autoFocus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
public class ChildFocusableLinearLayout extends LinearLayout implements View.OnFocusChangeListener {

    protected int mSelectedPosition = -1;

    private boolean blockKeycodeLeft;

    private boolean blockKeycodeRight;

    private boolean blockKeycodeDown;

    private boolean blockKeycodeUp;

    public ChildFocusableLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ChildFocusableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChildFocusableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChildFocusableLinearLayout);
            blockKeycodeUp = a.getBoolean(R.styleable.ChildFocusableLinearLayout_block_keycode_up, false);
            blockKeycodeDown = a.getBoolean(R.styleable.ChildFocusableLinearLayout_block_keycode_down, false);
            blockKeycodeLeft = a.getBoolean(R.styleable.ChildFocusableLinearLayout_block_keycode_left, false);
            blockKeycodeRight = a.getBoolean(R.styleable.ChildFocusableLinearLayout_block_keycode_right, false);
            a.recycle();
        }

//        setChildrenDrawingOrderEnabled(true);
//        setWillNotDraw(true); // 自身不作onDraw处理
////        setHasFixedSize(true);
//        setOverScrollMode(View.OVER_SCROLL_NEVER);
//
//        setClipChildren(false);
//        setClipToPadding(false);

        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
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
        Log.e("lhz", "View Tag:" + getTag() + "===" + "childCount:" + count);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.isFocusable()) {
                child.setOnFocusChangeListener(ChildFocusableLinearLayout.this);
            }
        }
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.e("lhz", "View Tag:" + getTag() + "===" + "requestFocus:direction..." + direction);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "requestFocus:hasFocus():" + hasFocus());
        onMyFocusChanged(this, true);
        if (null == getFocusedChild()) {
            //请求默认焦点
            requestDefaultFocus(direction);
        }
        return true;
//        return false;
    }

    /**
     * 子类可重写此方法，监听当前ViewGroup是否拿到焦点。
     *
     * @param view     自身view
     * @param hasFocus 是否获得焦点
     */
    protected void onMyFocusChanged(View view, boolean hasFocus) {
        Log.e("lhz", "View Tag:" + getTag() + "===" + "onMyFocusChanged---hasFocus->" + hasFocus);
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
            if (!hasFocus()) {
                //模拟获取焦点
                setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
            }

            Log.e("lhz", "View Tag:" + getTag() + "===" + "requestDefaultFocus--child index:" + mSelectedPosition);
//            view.postDelayed(new Runnable() {
//                @Override
//                public void run() {
            view.requestFocus();
//                }
//            }, 10);

        }
    }

    @Override
    public void onFocusChange(View v, final boolean hasFocus) {
        Log.e("lhz", "View Tag:" + getTag() + "===" + "onFocusChange:view--" + v.getTag() + ",hasFocus:" + hasFocus);
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

        //回调父类tvRecylerView中的onFocusChange方法
        ViewParent parent = getParent();
        if (parent instanceof OnFocusChangeListener) {
            OnFocusChangeListener viewGroup = (OnFocusChangeListener) parent;
            viewGroup.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean result = super.dispatchKeyEvent(event);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "dispatchKeyEvent（）start：result=" + result);
        if (!result) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (blockKeycodeUp && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP && findNextFocus(keyCode2Direction(event.getKeyCode())) == null) {
                        return true;
                    }
                    if (blockKeycodeDown && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN && findNextFocus(keyCode2Direction(event.getKeyCode())) == null) {
                        return true;
                    }
                    if (blockKeycodeLeft && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT && findNextFocus(keyCode2Direction(event.getKeyCode())) == null) {
                        return true;
                    }
                    if (blockKeycodeRight && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT && findNextFocus(keyCode2Direction(event.getKeyCode())) == null) {
                        return true;
                    }
                    result = handleKeyDown(event.getKeyCode(), event);
                    break;
                case KeyEvent.ACTION_UP:
                    result = onKeyUp(event.getKeyCode(), event);
                    break;
            }
        }
        Log.e("lhz", "View Tag:" + getTag() + "===" + "dispatchKeyEvent（）end：result=" + result);
        return result;
    }

//    @Override
//    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
//        Loger.i("gainFocus=" + gainFocus + " hasFocus=" + hasFocus() + " direction=" + direction);
//        onFocusChange(gainFocus);
//        if (gainFocus) {
//            setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
//        } else {
//            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
//        }
//        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//    }

    /**
     * 处理onKeyDown等事件
     *
     * @param keyCode
     * @param event
     * @return
     */
    private boolean handleKeyDown(int keyCode, KeyEvent event) {
        int direction = keyCode2Direction(keyCode);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "handleKeyDown.direction:" + direction);
        if (direction == -1) {
            return false;
        }

        final View nextFocusedView = findNextFocus(direction);
        Log.e("lhz", "View Tag:" + getTag() + "===" + "find next foucs view:" + (nextFocusedView == null ? "false" : true));
        if (null != nextFocusedView) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) == nextFocusedView) {
                    mSelectedPosition = i;
                    Log.e("lhz", "View Tag:" + getTag() + "===" + "next Focus index:" + i);
                    break;
                }
            }
            nextFocusedView.requestFocus();
            return true;
        } else {
            onMyFocusChanged(this, false);
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
            return false;
        }
//        return false;
    }

    /**
     * 查找下个可获取焦点的view
     *
     * @param direction
     * @return
     */
    private View findNextFocus(int direction) {
        return FocusFinder.getInstance().findNextFocus(this, getFocusedChild(), direction);
    }

    /**
     * keycode值转成Direction值
     *
     * @param keyCode
     * @return
     */
    private int keyCode2Direction(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                return FOCUS_DOWN;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                return FOCUS_RIGHT;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                return FOCUS_LEFT;

            case KeyEvent.KEYCODE_DPAD_UP:
                return FOCUS_UP;

            default:
                return -1;
        }
    }

}
