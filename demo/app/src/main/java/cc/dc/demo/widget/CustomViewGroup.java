package cc.dc.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dc on 16/6/18.
 */
public class CustomViewGroup extends ViewGroup{

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = 0;
        int parentHeight = 0;
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                CustomLayoutParams params = (CustomLayoutParams) child.getLayoutParams();
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                parentWidth += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                parentHeight += child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }

            parentWidth += getPaddingLeft() + getPaddingRight();
            parentHeight += getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(resolveSize(parentWidth, widthMeasureSpec),
                resolveSize(parentHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        if (getChildCount() > 0) {
            int height = 0;
            for (int i = 0 ; i < getChildCount(); i ++) {
                View child = getChildAt(i);
                CustomLayoutParams params = (CustomLayoutParams) child.getLayoutParams();
                child.layout(
                        paddingLeft + params.leftMargin,
                        height + paddingTop + params.topMargin,
                        child.getMeasuredWidth() + paddingLeft + params.leftMargin,
                        child.getMeasuredHeight() + height + paddingTop + params.topMargin);
                height += child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    private static class CustomLayoutParams extends MarginLayoutParams {
        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
