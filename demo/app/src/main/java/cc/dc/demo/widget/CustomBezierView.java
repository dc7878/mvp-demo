package cc.dc.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by dc on 16/7/31.
 */
public class CustomBezierView extends View implements View.OnClickListener{

    private Paint mPaint;

    private float startX;
    private float startY;

    private float endX;
    private float endY;

    //控制点
    private float mAuxiliaryX;
    private float mAuxiliaryY;

    private Path mPath = new Path();

    private float mScreenW;
    private float mScreenH;
    private float waveWidth = 1000;
    private int waveCount;
    private float mCenterY;

    private float mOffset;


    public CustomBezierView(Context context) {
        this(context, null);
    }

    public CustomBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        startX = w / 4;
//        startY = h / 2 - 200;
//
//        endX = w / 4 *3;
//        endY = h / 2 - 200;

        mScreenW = w;
        mScreenH = h;
        waveCount = Math.round(1.5f + mScreenW / waveWidth);
        waveCount = 2;
        mCenterY = mScreenH * 3 / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(- waveWidth + mOffset, mCenterY);
        for (int i = 0 ; i < waveCount; i++) {
            mPath.quadTo(-waveWidth * 3 /4 + (i * waveWidth) + mOffset,
                    mCenterY + 60,
                    -waveWidth / 2 + (i * waveWidth) + mOffset,
                    mCenterY);
            mPath.quadTo(-waveWidth / 4 + i * waveWidth+ mOffset,
                    mCenterY - 60,
                    i * waveWidth + mOffset,
                    mCenterY);
        }
        mPath.lineTo(mScreenW, mScreenH);
        mPath.lineTo(0, mScreenH);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                mAuxiliaryX = event.getX();
//                mAuxiliaryY = event.getY();
//                invalidate();
//                break;
//        }
//
//        return true;
//    }

    @Override
    public void onClick(View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0, (int) waveWidth);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
//                mCenterY -= 5;
                postInvalidate();
            }
        });
        animator.start();
    }
}
