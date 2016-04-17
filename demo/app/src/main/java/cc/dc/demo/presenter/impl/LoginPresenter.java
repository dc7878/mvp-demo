package cc.dc.demo.presenter.impl;

import android.content.Context;

import cc.dc.demo.listener.ILoginListener;
import cc.dc.demo.model.ILoginModel;
import cc.dc.demo.model.impl.LoginModel;
import cc.dc.demo.presenter.ILoginPresenter;
import cc.dc.demo.view.ILoginView;

/**
 * Created by dingcai on 2016/4/7.
 */
public class LoginPresenter implements ILoginPresenter, ILoginListener{
    private Context context;
    private ILoginView loginView;
    private ILoginModel loginModel;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        this.loginModel = new LoginModel(this);
    }

    @Override
    public void login(String username, String password) {
        loginView.showLoading("正在登录");
        loginModel.login(username, password);
    }

    @Override
    public void loginCode(String code, String password) {
        loginModel.loginCode();
    }

    @Override
    public void loginSuccess(String message) {
        loginView.hideLoading();
        loginView.loginSuccess(message);
    }

    @Override
    public void loginFail(String message) {
        loginView.hideLoading();
        loginView.loginFail(message);
    }
}
