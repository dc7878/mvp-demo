package cc.dc.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by dc on 16/7/23.
 */
public class CustomMoveView extends TextView{
    private int lastX;
    private int lastY;

    public CustomMoveView(Context context) {
        this(context, null);
    }

    public CustomMoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                lastX = x;
                lastY = y;
                break;
        }
        return true;
    }
}
