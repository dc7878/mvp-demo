package cc.dc.demo.model.impl;

import cc.dc.demo.listener.ILoginListener;
import cc.dc.demo.model.ILoginModel;

/**
 * Created by dingcai on 2016/4/7.
 */
public class LoginModel implements ILoginModel{
    private ILoginListener loginListener;

    public LoginModel(ILoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void login(String username, String password) {
        if ("dc7878".equals(username)) {
            loginListener.loginSuccess("登录成功");
        }else {
            loginListener.loginFail("登录失败");
        }
    }

    @Override
    public void loginCode() {

    }
}
