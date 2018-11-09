package test.lhz.com.testanimator.player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.ui.PlayerControlView;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/19
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public abstract class BasePlayerControlView extends FrameLayout {

    public BasePlayerControlView(@NonNull Context context) {
        super(context);
    }

    public BasePlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract PlayerControlView getPlayerControlView();

}
