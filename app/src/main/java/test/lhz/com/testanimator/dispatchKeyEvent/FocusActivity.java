package test.lhz.com.testanimator.dispatchKeyEvent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/21
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class FocusActivity extends FragmentActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, FocusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main_dispatch);
        FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(container.getId(), mainFragment, "MainFragment").commitAllowingStateLoss();

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("lhz", "FocusActivity---dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("lhz", "FocusActivity---onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("lhz", "FocusActivity---onKeyUp");
        return super.onKeyUp(keyCode, event);
    }
}
