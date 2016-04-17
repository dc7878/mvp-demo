package cc.dc.demo.ui.activity;

import android.text.TextUtils;
import android.webkit.WebView;

import butterknife.InjectView;
import cc.dc.demo.R;
import cc.dc.demo.ui.activity.base.BaseActivity;

/**
 * Created by dingcai on 2016/4/6.
 */
public class WebActivity extends BaseActivity{
    @InjectView(R.id.wv_web)
    WebView webView;

    @Override
    protected void initViewAndListener() {
        String url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            webView.loadUrl("http://www.jianshu.com/p/a6f158d1a9c9");
        } else {
            webView.loadUrl(url);
        }
    }

    @Override
    protected boolean isButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }
}
