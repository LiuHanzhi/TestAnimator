package test.lhz.com.testanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
        setContentView(R.layout.activity_animation_main);
        final View view2 = findViewById(R.id.view2);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimate1(view2, false);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimate1(view2, true);
            }
        });
    }

    /**
     * 属性动画
     * 平移
     */
    private void setAnimate1(View view, boolean reverse) {
//      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
//      创建属性动画对象，并设置移动的方向和偏移量
//      translationX是imageview的平移属性
        ObjectAnimator objectAnimator;
        if (reverse) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", -200, 0);
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, -200);
        }
//      设置移动时间
        objectAnimator.setDuration(1000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
//      开始动画
        objectAnimator.start();
    }

}
