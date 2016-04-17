package cc.dc.demo.presenter.impl;

import android.content.Context;

import cc.dc.demo.model.IMainModel;
import cc.dc.demo.model.impl.MainModeImpl;
import cc.dc.demo.presenter.IMainPresenter;
import cc.dc.demo.view.IMainView;

/**
 * Created by dingcai on 2016/4/5.
 */
public  class MainPresenterImpl implements IMainPresenter {
    private Context context;
    private IMainView mainView;
    private IMainModel mainModel;

    public MainPresenterImpl(Context context, IMainView mainView) {
        this.context = context;
        this.mainView = mainView;
        this.mainModel = new MainModeImpl();
    }

    @Override
    public void initMain() {
        mainView.initItemNames(mainModel.getAllItemNames());
        mainView.initFragments(mainModel.getAllFragments());
    }

    @Override
    public void changeSelectPosition(int position) {
        mainView.changeSelectPosition(position);
    }
}
