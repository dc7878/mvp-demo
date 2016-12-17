package cc.dc.demo.listener;

import cc.dc.demo.bean.AndroidData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dc on 16/9/4.
 */
public interface GankIo {

    @GET("data/Android/10/1")
    Call<AndroidData> getAndroidData();

    @GET("data/Android/10/2")
    Call<AndroidData> getAndroidDatas();
}
