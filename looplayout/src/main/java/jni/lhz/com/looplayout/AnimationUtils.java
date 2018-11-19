package jni.lhz.com.looplayout;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {

    public static void doForwardAnimation(View frontView, View behindView, Animation.AnimationListener animationListener) {
        TranslateAnimation frontTranslation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1f);
        TranslateAnimation behindTranslation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);

        frontTranslation.setDuration(1000);
        behindTranslation.setDuration(1000);
        frontView.startAnimation(frontTranslation);
        behindView.startAnimation(behindTranslation);

        frontTranslation.setAnimationListener(animationListener);
    }

}
