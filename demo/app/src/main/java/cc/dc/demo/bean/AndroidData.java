package cc.dc.demo.bean;

import java.util.List;

/**
 * Created by dc on 16/9/4.
 */
public class AndroidData {
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
