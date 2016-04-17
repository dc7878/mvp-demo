package cc.dc.demo.presenter;

/**
 * Created by dingcai on 2016/4/7.
 */
public interface ILoginPresenter {
    void login(String username, String password);
    void loginCode(String code,String password);
}
