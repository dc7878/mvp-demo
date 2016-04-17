package cc.dc.demo.view;

import java.util.List;

import cc.dc.demo.bean.AndroidBean;
import cc.dc.demo.view.base.IBaseView;

/**
 * Created by dingcai on 2016/4/6.
 */
public interface IAndroidView extends IBaseView {
    void refresh(List<AndroidBean> list);
    void addMore(List<AndroidBean> list);
}
