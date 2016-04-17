package cc.dc.demo.model.impl;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cc.dc.demo.DemoApplication;
import cc.dc.demo.bean.AndroidBean;
import cc.dc.demo.json.AndroidJson;
import cc.dc.demo.listener.ILoadListListener;
import cc.dc.demo.model.IAndroidModel;

/**
 * Created by dingcai on 2016/4/6.
 */
public class AndroidModeImpl implements IAndroidModel {
    private ILoadListListener loadListListener;

    public AndroidModeImpl(ILoadListListener loadListListener){
        this.loadListListener = loadListListener;
    }

    @Override
    public void loadData(final int eventType, String url, int pageNumber, final int size) {
        url = url + size + File.separator + pageNumber;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Gson gson = new Gson();
                    AndroidJson androidJson = gson.fromJson(response, AndroidJson.class);
                    if (androidJson != null && androidJson.getResults() != null && androidJson.getResults().size() > 0) {
                        if (size == 40) {
                            List<AndroidBean> list = androidJson.getResults();
                            List<AndroidBean> listRemove = new ArrayList<>();
                            for (AndroidBean bean:list){
                                if (!bean.getType().equals("iOS") && !bean.getType().equals("Android")) {
                                    listRemove.add(bean);
                                }
                            }
                            list.removeAll(listRemove);
                            loadListListener.loadSuccess(eventType, list);
                        }else {
                            loadListListener.loadSuccess(eventType, androidJson.getResults());
                        }
                    } else  {
                        loadListListener.loadEmpty("暂时没有数据");
                    }
                } catch (Exception e) {
                    loadListListener.loadException(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    loadListListener.loadNetError(error.getMessage());
                    return;
                }
                loadListListener.loadError(error.getMessage());
            }
        });
        DemoApplication.getInstance().getRequestQueue().add(stringRequest);
    }
}
