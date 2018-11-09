package test.lhz.com.testanimator.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

import com.google.android.exoplayer2.ui.PlayerControlView;

import test.lhz.com.testanimator.R;
import test.lhz.com.testanimator.UITool;


/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/12
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class HalfPlayerControlView extends BasePlayerControlView {


    public HalfPlayerControlView(Context context) {
        super(context);
        initLayout();
    }

    public HalfPlayerControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public HalfPlayerControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    @Override
    public PlayerControlView getPlayerControlView() {
        return (PlayerControlView) UITool.inflate(getContext(), R.layout.half_player_control_view,this,false);
    }

    private void initLayout() {
        UITool.inflate(getContext(), R.layout.half_player_control_view,this,false);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz","CustomPlayerControlView---dispatchKeyEvent:"+event.getKeyCode());
        if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER){

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
