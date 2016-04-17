package cc.dc.demo.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;


import java.util.List;

import butterknife.InjectView;
import cc.dc.demo.R;
import cc.dc.demo.presenter.IMainPresenter;
import cc.dc.demo.presenter.impl.MainPresenterImpl;
import cc.dc.demo.ui.activity.base.BaseActivity;
import cc.dc.demo.ui.adapter.MainPageAdapter;
import cc.dc.demo.ui.fragment.base.BaseFragment;
import cc.dc.demo.view.IMainView;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener{
    @InjectView(R.id.vp_main_content)
    ViewPager viewPager;
    @InjectView(R.id.tv_main_item_1)
    TextView tvItem1;
    @InjectView(R.id.tv_main_item_2)
    TextView tvItem2;
    @InjectView(R.id.tv_main_item_3)
    TextView tvItem3;

    private MainPageAdapter mainPageAdapter;
    private IMainPresenter mainPresenter;

    @Override
    protected boolean isButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAndListener() {
        mainPresenter  = new MainPresenterImpl(this,this);
        mainPresenter.initMain();

        tvItem1.setOnClickListener(this);
        tvItem2.setOnClickListener(this);
        tvItem3.setOnClickListener(this);
    }

    @Override
    public void initItemNames(List<String> names) {
        tvItem1.setText(names.get(0));
        tvItem2.setText(names.get(1));
        tvItem3.setText(names.get(2));
    }

    @Override
    public void initFragments(List<BaseFragment> list) {
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(mainPageAdapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    public void changeSelectPosition(int position) {
        switch (position){
            case 0:
                tvItem1.setEnabled(true);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        int position = -1;
        switch (v.getId()){
            case R.id.tv_main_item_1:
                position = 0;
                break;
            case R.id.tv_main_item_2:
                position = 1;
                break;
            case R.id.tv_main_item_3:
                position = 2;
                break;
        }
        mainPresenter.changeSelectPosition(position);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            mainPresenter.changeSelectPosition(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
