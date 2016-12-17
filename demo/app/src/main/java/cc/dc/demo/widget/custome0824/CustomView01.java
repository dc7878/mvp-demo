package cc.dc.demo.widget.custome0824;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 16/8/24.
 */
public class CustomView01 extends View {

    private Paint mPaint;

    public CustomView01(Context context) {
        this(context, null);
    }

    public CustomView01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(200, 200);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawCircle(0, 0, 100, mPaint);
//
//        mPaint.setColor(Color.YELLOW);
////        canvas.translate(200, 200);
//        canvas.scale(0.5f, 0.5f);
//        canvas.drawCircle(0, 0, 100, mPaint);
//
//
//        canvas.translate(400, 400);
//
//        Rect rect = new Rect(0, 0, 300, 200);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawRect(rect, mPaint);
//
//        mPaint.setColor(Color.CYAN);
//        canvas.scale(-1f, -3f);
//        canvas.drawRect(rect, mPaint);


//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        mPaint.setColor(Color.BLACK);
//
//        RectF rectF = new RectF(-400, -400, 400, 400);
//        for (int i = 0; i < 20; i++) {
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawRect(rectF, mPaint);
//        }


//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        RectF rectF = new RectF(-200, -100, 200, 100);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.rotate(45);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawRect(rectF, mPaint);


//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawCircle(0, 0, 150, mPaint);
//        canvas.drawCircle(0, 0, 200, mPaint);
//        mPaint.setColor(Color.BLUE);
//        for (int i = 0; i < 60; i++) {
//            canvas.rotate(10);
//            canvas.drawLine(0, 150 + 5, 0, 200 -5, mPaint);
//        }

        canvas.drawColor(Color.RED);

        mPaint.setColor(Color.CYAN);
        canvas.drawRect(10, 10, 100, 100, mPaint);


        canvas.save();
        canvas.translate(100, 100);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(10, 10, 100, 100, mPaint);
//        canvas.drawColor(Color.YELLOW);
        canvas.restore();

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(300, 300, 500, 500, mPaint);


        canvas.translate(100, 100);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(10, 300, 100, 390, mPaint);


        mPaint.setColor(Color.GRAY);
        canvas.drawRect(300, 500, 500, 700, mPaint);
    }
}
