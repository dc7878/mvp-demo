package cc.dc.demo.listener;

import java.util.List;

import cc.dc.demo.bean.AndroidBean;

/**
 * Created by dingcai on 2016/4/6.
 */
public interface ILoadListListener {
    void loadSuccess(int evenType,List<AndroidBean> list);
    void loadNetError(String message);
    void loadException(String message);
    void loadEmpty(String message);
    void loadError(String message);
}
