package cc.dc.demo.listener;

/**
 * Created by dingcai on 2016/4/7.
 */
public interface ILoginListener {
    void loginSuccess(String message);
    void loginFail(String message);
}
