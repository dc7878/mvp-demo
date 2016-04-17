package cc.dc.demo.json;

import java.util.List;

import cc.dc.demo.bean.AndroidBean;

/**
 * Created by dingcai on 2016/4/6.
 */
public class AndroidJson {
    private boolean error;
    private List<AndroidBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<AndroidBean> getResults() {
        return results;
    }

    public void setResults(List<AndroidBean> results) {
        this.results = results;
    }
}
