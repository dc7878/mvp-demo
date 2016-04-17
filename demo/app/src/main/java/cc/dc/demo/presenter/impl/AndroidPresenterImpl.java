package cc.dc.demo.presenter.impl;

import android.content.Context;

import java.util.List;

import cc.dc.demo.api.ApiUtil;
import cc.dc.demo.bean.AndroidBean;
import cc.dc.demo.listener.ILoadListListener;
import cc.dc.demo.model.IAndroidModel;
import cc.dc.demo.model.impl.AndroidModeImpl;
import cc.dc.demo.presenter.IAndroidPresenter;
import cc.dc.demo.view.IAndroidView;

/**
 * Created by dingcai on 2016/4/6.
 */
public class AndroidPresenterImpl implements IAndroidPresenter,ILoadListListener {
    private Context context;
    private IAndroidView androidView;
    private IAndroidModel androidModel;

    public AndroidPresenterImpl(Context context, IAndroidView androidView) {
        this.context = context;
        this.androidView = androidView;
        this.androidModel = new AndroidModeImpl(this);
    }

    @Override
    public void loadData(boolean isFirst,int eventType, String url, int pageNumber, int size) {
        if(isFirst){
            androidView.showLoading("正在加载...");
        }
        androidModel.loadData(eventType, url, pageNumber, size);
    }

    @Override
    public void loadSuccess(int eventType,List<AndroidBean> list) {
        androidView.hideLoading();
        if (eventType == ApiUtil.EventType.EVENT_REFRESH) {
            androidView.refresh(list);
        } else if(eventType == ApiUtil.EventType.EVENT_MORE) {
            androidView.addMore(list);
        }
    }

    @Override
    public void loadEmpty(String message) {
        androidView.hideLoading();
        androidView.showEmpty(message);
    }

    @Override
    public void loadNetError(String message) {
        androidView.hideLoading();
        androidView.showEmpty(message);
    }

    @Override
    public void loadException(String message) {
        androidView.hideLoading();
        androidView.showEmpty(message);
    }

    @Override
    public void loadError(String message) {
        androidView.hideLoading();
        androidView.showEmpty(message);
    }

    @Override
    public void onItemClick(AndroidBean bean) {

    }
}
