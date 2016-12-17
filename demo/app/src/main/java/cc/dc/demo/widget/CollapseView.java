package cc.dc.demo.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.dc.demo.R;

/**
 * Created by dc on 16/9/25.
 */
public class CollapseView extends LinearLayout{

    private int parentWidthMeasureSpec;

    private int parentHeightMeasureSpec;

    private View view;

    private TextView txtNumber;

    private TextView txtContent;

    private TextView txtStatus;

    private RelativeLayout layerContent;

    private boolean currentStatus;

    private final int DURATION = 300;

    private Context context;

    public CollapseView(Context context) {
        this(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layer_collapse_view,null);
        addView(view);
        initView();
        setNormalContent();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    private void initView() {
        txtNumber = (TextView) view.findViewById(R.id.txt_number);
        txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtStatus = (TextView) view.findViewById(R.id.txt_status);

        layerContent = (RelativeLayout) view.findViewById(R.id.layer_image);

        txtStatus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
            }
        });

        collapse();
    }

    private void setNormalContent() {
        View viewContent = LayoutInflater.from(context).inflate(R.layout.layer_content, null);
        layerContent.addView(viewContent);
    }

    public void setTxtNumber(int number) {
        if (number >= 0) {
            txtNumber.setText(String.valueOf(number));
        }
    }

    public void setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            txtContent.setText(content);
        }
    }

    private void changeStatus() {
        if (currentStatus) {
            collapse();
        } else {
            expand();
        }

        currentStatus = !currentStatus;
    }

    private void expand() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        layerContent.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measureHeight = layerContent.getMeasuredHeight();
        layerContent.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                layerContent.getLayoutParams().height = (int)(measureHeight * interpolatedTime);
                layerContent.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {

                return true;
            }
        };

        animation.setDuration(DURATION);
        layerContent.startAnimation(animation);
    }

    private void collapse() {
        final int measureHeight = layerContent.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                if (interpolatedTime == 1) {
                    layerContent.setVisibility(GONE);
                } else {
                    layerContent.getLayoutParams().height = measureHeight - (int) (interpolatedTime * measureHeight);
                    layerContent.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {

                return true;
            }
        };

        animation.setDuration(DURATION);
        layerContent.startAnimation(animation);
    }

}
