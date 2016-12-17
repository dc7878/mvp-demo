package cc.dc.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import cc.dc.demo.R;
import cc.dc.demo.bean.AndroidBean;
import cc.dc.demo.bean.AndroidData;
import cc.dc.demo.listener.GankIo;
import cc.dc.demo.widget.CollapseView;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dc on 16/6/18.
 */
public class TestCustomActivity extends Activity{

    private static String TAG = "TestCustomActivity";

    private CollapseView collapseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_0925);

        collapseView = (CollapseView) findViewById(R.id.collapse_view);

        collapseView.setTxtNumber(123);
        collapseView.setContent("这是一条数据的内容");

        new Thread(){
            @Override
            public void run() {
                network();
            }
        }.start();

    }

    public void onClickButton(View view) {
        network();
    }


    private void network() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankIo gankIo = retrofit.create(GankIo.class);

        Call<AndroidData> call = gankIo.getAndroidData();
//        gankIo.getAndroidDatas();

        try {
            AndroidData data = call.execute().body();
            for(AndroidBean bean:data.getResults()) {
                Log.e("TestCustomActivity", "" + bean.getDesc() + "---" + bean.getWho());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
