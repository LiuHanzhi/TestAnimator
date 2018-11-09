package test.lhz.com.testanimator.dispatchKeyEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/21
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MButton extends android.support.v7.widget.AppCompatButton {
    public MButton(Context context) {
        super(context);
    }

    public MButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz","MButton---dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("lhz","MButton---onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("lhz","MButton---onKeyUp");
        return super.onKeyUp(keyCode, event);
    }
}
