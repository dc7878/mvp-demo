package cc.dc.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by dc on 16/9/27.
 */
public class FontView extends View{
    private final String TEXT = "p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";

    private Paint paint;
    private Paint.FontMetrics fontMetrics;

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);


        fontMetrics = paint.getFontMetrics();

        Log.e("FontView", fontMetrics.top + "");
        Log.e("FontView", fontMetrics.ascent + "");
        Log.e("FontView", fontMetrics.leading + "");
        Log.e("FontView", fontMetrics.descent + "");
        Log.e("FontView", fontMetrics.bottom + "");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(TEXT, 0, Math.abs(fontMetrics.top), paint);
    }
}
