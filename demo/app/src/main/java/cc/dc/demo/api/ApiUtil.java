package cc.dc.demo.api;

/**
 * Created by dingcai on 2016/4/5.
 */
public class ApiUtil {
    public static class GankApi{
        public static final String GANK_ANDROID="http://gank.io/api/data/Android/";
        public static final String GANK_IOS = "http://gank.io/api/data/iOS/";
        public static final String GANK_ALL = "http://gank.io/api/data/all/";
    }

    public static class EventType{
        public static final int EVENT_REFRESH = 10000;
        public static final int EVENT_MORE = 10001;
    }
}
