package cc.dc.demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import cc.dc.demo.R;
import cc.dc.demo.api.ApiUtil;
import cc.dc.demo.bean.AndroidBean;
import cc.dc.demo.presenter.IAndroidPresenter;
import cc.dc.demo.presenter.impl.AndroidPresenterImpl;
import cc.dc.demo.ui.activity.WebActivity;
import cc.dc.demo.ui.adapter.AndroidAdapter;
import cc.dc.demo.ui.fragment.base.BaseFragment;
import cc.dc.demo.view.IAndroidView;

/**
 * Created by dingcai on 2016/4/5.
 */
public class AndroidFragment extends BaseFragment implements IAndroidView,AdapterView.OnItemClickListener{
    @InjectView(R.id.lv_list)
    ListView listView;
    @InjectView(R.id.pb_loading)
    ProgressBar progressBar;
    @InjectView(R.id.tv_load_fail)
    TextView tvEmpty;

    private int pageNumber = 1;
    private final int SIZE = 20;
    private List<AndroidBean> infos;
    private AndroidAdapter androidAdapter;
    private IAndroidPresenter androidPresenter;

    @Override
    protected void onFirstVisible() {
        infos = new ArrayList<>();
        androidAdapter = new AndroidAdapter(context, infos);
        listView.setAdapter(androidAdapter);
        listView.setOnItemClickListener(this);

        androidPresenter = new AndroidPresenterImpl(context,this);
        androidPresenter.loadData(true, ApiUtil.EventType.EVENT_REFRESH, ApiUtil.GankApi.GANK_ANDROID,
                pageNumber, SIZE);
    }

    @Override
    public void refresh(List<AndroidBean> list) {
        if (list != null && list.size() > 0) {
            infos.clear();
            infos.addAll(list);
            androidAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addMore(List<AndroidBean> list) {
        if (list != null && list.size() > 0) {
            infos.addAll(list);
            androidAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showEmpty(String message) {
        listView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvEmpty.setText(message);
    }

    @Override
    public void showLoading(String message) {
        listView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //androidPresenter.onItemClick(infos.get(position));
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", infos.get(position).getUrl());
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_only;
    }

    @Override
    protected boolean isButterKnife() {
        return true;
    }
}
