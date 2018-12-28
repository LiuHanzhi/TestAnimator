package test.lhz.com.testanimator.animation3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/8/22
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main3);
        final View view2 = findViewById(R.id.view2);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = getView();
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
                parent.addView(view);
                setAnimate1(view, false);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimate1(view, true);
            }
        });
    }

    private int index = 0;

    private View view;

    private View getView() {
        int i = index % 4;
        index++;
        if (i == 0) {
            return findViewById(R.id.view1);
        } else if (i == 1) {
            return findViewById(R.id.view2);
        } else if (i == 2) {
            return findViewById(R.id.view3);
        } else {
            return findViewById(R.id.view4);
        }
    }

    /**
     * 属性动画
     * 平移
     */
    private void setAnimate1(View view, boolean reverse) {
//      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
//      创建属性动画对象，并设置移动的方向和偏移量
//      translationX是imageview的平移属性
        float scale = reverse ? 1.0f : 1.2f;
        view.animate().scaleX(scale).scaleY(scale).setDuration(1000).start();
    }


}
