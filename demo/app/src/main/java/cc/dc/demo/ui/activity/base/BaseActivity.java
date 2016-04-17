package cc.dc.demo.ui.activity.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by dingcai on 2016/4/5.
 */
public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId()!=0){
            setContentView(getLayoutId());
        }else {
            new IllegalStateException("layout 不能为空");
        }
        if(isButterKnife()){
            ButterKnife.inject(this);
        }
        initViewAndListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    protected abstract void initViewAndListener();

    protected abstract boolean isButterKnife();

    protected abstract int getLayoutId();
}
