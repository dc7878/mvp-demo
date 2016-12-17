package com.cc.dc.rxjavademo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dc on 16/5/2.
 */
public class RxJavaApplcation extends Application{

    private static RxJavaApplcation instance;

    private RequestQueue mRequestQueue;

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

    public static RxJavaApplcation getInstance(){
        return instance;
    }
}
