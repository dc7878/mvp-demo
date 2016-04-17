package cc.dc.demo.view;

import cc.dc.demo.view.base.IBaseView;

/**
 * Created by dingcai on 2016/4/7.
 */
public interface ILoginView extends IBaseView{
    void loginSuccess(String message);
    void loginFail(String message);
}
