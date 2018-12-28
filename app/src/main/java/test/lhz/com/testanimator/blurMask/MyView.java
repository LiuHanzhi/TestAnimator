package test.lhz.com.testanimator.blurMask;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private Paint mShadowPaint;

    private int mShadowColor = Color.RED;

    private float mShadowWidth = 20;

    public MyView(Context context) {
        this(context, null, 0);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mShadowPaint = new Paint();
        mShadowPaint.setColor(mShadowColor);
        mShadowPaint.setAntiAlias(true); //抗锯齿功能，会消耗较大资源，绘制图形速度会变慢
        mShadowPaint.setDither(true); //抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowWidth, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(mShadowWidth, mShadowWidth, getWidth() - mShadowWidth, getHeight() - mShadowWidth, mShadowPaint);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mShadowPaint);
    }
}