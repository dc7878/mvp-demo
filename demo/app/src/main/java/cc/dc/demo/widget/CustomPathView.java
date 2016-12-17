package cc.dc.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 16/6/19.
 */
public class CustomPathView extends View{
    private Paint mPaint;

    private Path mPath;

    private PathEffect pathEffect;

    private float phase = 2;

    public CustomPathView(Context context) {
        super(context);
        init(context);
    }

    public CustomPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        pathEffect = new DashPathEffect(new float[]{20,10},phase);

        mPath = new Path();
        mPath.moveTo(10, 0);
        for (int i=0; i< 20;i++){
            mPath.lineTo(i * 30, (float) (Math.random() * 100));
        }

        ValueAnimator animator = ValueAnimator.ofInt(100, 1);
        animator.setDuration(20000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                phase = value * 10;
                invalidate();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathEffect = new DashPathEffect(new float[]{20,10},phase);
        mPaint.setPathEffect(pathEffect);
        canvas.drawPath(mPath, mPaint);
    }
}
