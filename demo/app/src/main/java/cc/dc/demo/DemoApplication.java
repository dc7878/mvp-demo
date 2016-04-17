package cc.dc.demo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dingcai on 2016/4/5.
 */
public class DemoApplication extends Application{
    private RequestQueue mRequestQueue;

    private static DemoApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public static DemoApplication getInstance(){
        return instance;
    }
}
