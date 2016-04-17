package cc.dc.demo.presenter;

import cc.dc.demo.bean.AndroidBean;

/**
 * Created by dingcai on 2016/4/6.
 */
public interface IAndroidPresenter {
    void loadData(boolean isFirst, int eventType, String url, int pageNumber, int size);
    void onItemClick(AndroidBean bean);
}
