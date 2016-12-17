package cc.dc.demo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import cc.dc.demo.R;

/**
 * Created by dc on 16/6/19.
 * 绘制圆形图片
 */
public class CustomCircleImage extends ImageView{
    private Context mContext;

    private Bitmap mBitmap = null;

    private Paint mPaint;

    private int width;
    private int height;

    public CustomCircleImage(Context context) {
        super(context);
        init(context);
    }

    public CustomCircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomCircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.image);
        width = mBitmap.getWidth();
        height = width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap b = getCircleBitmap(mBitmap);
        Rect rect = new Rect(0, 0, width, height);
        canvas.drawBitmap(b, rect, rect, new Paint());
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap circleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);
        mPaint.setColor(0xff000000);
        mPaint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, width, height);
        canvas.drawCircle(width/2, height/2, width/2, mPaint);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        return circleBitmap;
    }
}
