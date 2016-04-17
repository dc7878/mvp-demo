package cc.dc.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cc.dc.demo.R;

/**
 * Created by dingcai on 2016/4/8.
 */
public class CustomeFloorView extends FrameLayout{
    private Drawable drawable;

    public CustomeFloorView(Context context) {
        super(context);
    }

    public CustomeFloorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_custome, null);
        View view2 = LayoutInflater.from(context).inflate(R.layout.layout_custome, null);
        addView(view1);
        addView(view2);
        drawable = context.getResources().getDrawable(R.drawable.custome_bg);
    }

    public CustomeFloorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i=0;i<getChildCount();i++){
            //getChildAt(i).draw(canvas);
            View view = getChildAt(i);
            drawable.setBounds(view.getLeft()+i*10,view.getTop()+i*10,view.getRight()-i*10,view.getBottom()-i*10);
            drawable.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }
}
