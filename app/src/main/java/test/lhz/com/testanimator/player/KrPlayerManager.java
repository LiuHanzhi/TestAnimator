package test.lhz.com.testanimator.player;

import com.google.android.exoplayer2.ui.PlayerControlView;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/19
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class KrPlayerManager implements KrPlayerView.KeyEventListener {

    private KrPlayerView krPlayerView;

    private boolean isFullView;

    public KrPlayerManager(KrPlayerView krPlayerView) {
        this.krPlayerView = krPlayerView;
    }

    private void init() {
        krPlayerView.setKeyEventListener(this);
    }

    public void setPlayControlView(PlayerControlView playControlView, boolean isFullView) {
        this.isFullView = isFullView;
        krPlayerView.setPlayControlView(playControlView);
    }

    @Override
    public boolean onBackKeyPress() {
        return false;
    }

    @Override
    public boolean onConfirmKeyPressed() {
        return false;
    }

    @Override
    public boolean onMenuKeyPressed() {
        return false;
    }

    @Override
    public boolean onUpKeyPressed() {
        return false;
    }

    @Override
    public boolean onDownKeyPressed() {
        return false;
    }
}
