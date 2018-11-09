package test.lhz.com.testanimator.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ui.PlayerControlView;

import test.lhz.com.testanimator.R;


/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/19
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class KrPlayerView extends PlayerView {


    private KeyEventListener keyEventListener;

    public KrPlayerView(Context context) {
        super(context);
        init();
    }

    public KrPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KrPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(true);
        requestFocus();
    }

    /**
     * 设置control view
     *
     * @param playControlView
     */
    public void setPlayControlView(PlayerControlView playControlView) {
        this.controller = playControlView;
        View controllerPlaceholder = findViewById(R.id.player_view_control);
        if (controllerPlaceholder != null) {
            controller.setLayoutParams(controllerPlaceholder.getLayoutParams());
            controller.setId(controllerPlaceholder.getId());
            ViewGroup parent = ((ViewGroup) controllerPlaceholder.getParent());
            int controllerIndex = parent.indexOfChild(controllerPlaceholder);
            parent.removeView(controllerPlaceholder);
            parent.addView(controller, controllerIndex);
        } else {
            addView(controller);
            controller.setId(R.id.player_view_control);
        }
        setUseController(true);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz","KrPlayerView---keyEvent");
        if (keyEventListener != null) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEventListener.onBackKeyPress()) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && keyEventListener.onConfirmKeyPressed()) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MENU && keyEventListener.onMenuKeyPressed()) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyEventListener.onUpKeyPressed()) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && keyEventListener.onDownKeyPressed()) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void setKeyEventListener(KeyEventListener keyEventListener) {
        this.keyEventListener = keyEventListener;
    }


    /**
     * 按键回调
     */
    public interface KeyEventListener {
        /**
         * 返回键
         *
         * @return
         */
        boolean onBackKeyPress();

        /**
         * 确认键
         *
         * @return
         */
        boolean onConfirmKeyPressed();

        /**
         * menu键
         *
         * @return
         */
        boolean onMenuKeyPressed();

        /**
         * 上键
         *
         * @return
         */
        boolean onUpKeyPressed();

        /**
         * 下键
         *
         * @return
         */
        boolean onDownKeyPressed();

    }
}
