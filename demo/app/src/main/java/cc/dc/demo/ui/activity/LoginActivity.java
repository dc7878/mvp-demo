package cc.dc.demo.ui.activity;

import android.widget.Toast;

import cc.dc.demo.R;
import cc.dc.demo.presenter.ILoginPresenter;
import cc.dc.demo.presenter.impl.LoginPresenter;
import cc.dc.demo.ui.activity.base.BaseActivity;
import cc.dc.demo.view.ILoginView;

/**
 * Created by dingcai on 2016/4/7.
 */
public class LoginActivity extends BaseActivity implements ILoginView{
    private ILoginPresenter loginPresenter;

    @Override
    protected void initViewAndListener() {
        loginPresenter = new LoginPresenter(this,this);
    }

    @Override
    public void loginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(LoginActivity.this, "隐藏加载...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean isButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
