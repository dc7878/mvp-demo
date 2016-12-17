package cc.dc.demo.ui.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.InjectView;
import cc.dc.demo.R;
import cc.dc.demo.ui.activity.base.BaseActivity;
import cc.dc.demo.widget.touch.CustomButton;

/**
 * Created by dc on 16/6/4.
 */
public class TouchTestActivity extends BaseActivity{

    @InjectView(R.id.btn_touch_test)
    CustomButton customButton;

    @Override
    protected void initViewAndListener() {
        customButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("CustomButton", "onTouch-->ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("CustomButton", "onTouch-->ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("CustomButton", "onTouch-->ACTION_UP");
                        break;
                }
                return false;
            }
        });
    }


    public void onClickButton(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_test);
        dialog.show();
        Window window = dialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    protected boolean isButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_touch_test;
    }
}
