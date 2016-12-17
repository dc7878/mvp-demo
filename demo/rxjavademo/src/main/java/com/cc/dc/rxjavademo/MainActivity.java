package com.cc.dc.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @InjectView(R.id.tv_main_info)
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.inject(this);
        //testRxJava1();
//        testRxJava2();
//        testRxJava3();
//        testRxJava4();
//        testRxJava5();
//        testRxJava6();
//        testRxJava7();
//        testRxJava8();
//        testRxJava9();
//        testRxJava10();
        testRxJava11();
    }

    private void testRxJava1(){
        /**
         * 观察者，比如OnClickListener的listener，决定受到收到消息之后如何响应
         */
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,""+s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.e(TAG, "subscriber  onStart");
            }

            @Override
            public void onCompleted() {
                Log.e(TAG,"subscriber   onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"subscriber  "+s);
            }
        };

        /**
         * 被观察者，比如OnClickListener的Button
         */
        Observable observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext("Hello Rxjava");
                subscriber.onCompleted();
            }
        });


        //observable.subscribe(subscriber);

        if(subscriber.isUnsubscribed()){
            Log.e(TAG, "subscriber  isUnsubscribed");
            //subscriber.unsubscribe();
        }
        //observable.subscribe(subscriber);

        String[] strings = {"INFO1","INFO2","INFO3"};
        Observable observable1 = Observable.from(strings);
        observable1.subscribe(observer);


        Action1<Throwable> actionError = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "actionError-->"+throwable.getMessage());
            }
        };

        Action1<String> actionNext = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG,"actionNext--->"+s);
            }
        };

        Action0 actionComplete = new Action0() {
            @Override
            public void call() {
                Log.e(TAG,"actionComplete--->");
            }
        };

        observable.subscribe(actionNext, actionError, actionComplete);
    }

    private void testRxJava2(){
        Log.e(TAG, Thread.currentThread().getName());
        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("onNext");
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "testRxJava2-->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "testRxJava2---onError--" + e.getMessage());
            }

            @Override
            public void onNext(final String s) {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
                Log.e(TAG, "testRxJava2-->onNext--" + s);
                tvInfo.setText(s);
            }
        });
    }

    private void testRxJava3(){
        /**
         * 通过map将just的原始数据转换成另外的数据类型(Bitmap等)
         * 这里只是将String--->String + "info"
         */
        Observable.just("message").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "info";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "testRxJava3-->" + s);
            }
        });
    }

    private void testRxJava4(){
        Student[] students = new Student[4];
        students[0] = new Student("dc7878",12);
        List<Course> courseList = new ArrayList<>();
        for (int i=0;i<4;i++){
            Course course = new Course("dc-->"+i,"dc7878-->"+i*10);
            courseList.add(course);
        }
        students[0].setCourseList(courseList);

        List<Course> courseList2 = new ArrayList<>();
        for (int i=0;i<3;i++){
            Course course = new Course("cc-->"+i*100,"dc7878-->"+i*1);
            courseList2.add(course);
        }
        students[1] = new Student("dc3547",22);
        students[1].setCourseList(courseList2);


        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {

                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG, "course-->" + course.getCourseName());
            }
        });
    }

    private void testRxJava5(){
        Observable.just("message")
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    tvInfo.setVisibility(View.VISIBLE);
                    Log.e(TAG,"testRxJava5---call--"+Thread.currentThread().getName());
                }
            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .map(new Func1<String, String>() {
                @Override
                public String call(String s) {
                    Log.e(TAG, "map call---" + Thread.currentThread().getName());
                    //网络请求
                    return s + "--info changed--" + Thread.currentThread().getName();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    Log.e(TAG, "onComplete-----" + Thread.currentThread().getName());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    Log.e(TAG, "Subscriber  onNext---" + Thread.currentThread().getName());
                    //loginListener.onSuccess();
                }
            });
    }

    private void testRxJava6(){
        final String[] strings = {"str1","str2","str3"};
        Observable.from(strings).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"testRxJava6---onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"testRxJava6-->"+s);
            }
        });

        Observable.from(strings).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " new info ";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

//        final Student student = new Student("dc7878",22);

        Observable.from(strings).subscribeOn(Schedulers.io()).map(new Func1<String, Student>() {
            @Override
            public Student call(String s) {
                try{
                    RequestFuture<JSONObject> future = RequestFuture.newFuture();
                    String url = "http://www.weather.com.cn/adat/sk/101010100.html";
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, future, future);
                    RxJavaApplcation.getInstance().getRequestQueue().add(req);
                    JSONObject object = future.get();
                    if(object != null){
                        Log.e(TAG,"object-->not null"+object.toString());
                        Log.e(TAG,"object-->not null");
                        Student student = new Student(s, 13);
                        List<Course> courseList = new ArrayList<Course>();
                        courseList.add(new Course(student.getName() + "-课程", "zhangsan"));
                        student.setCourseList(courseList);
                        return student;
                    }else {
                        Log.e(TAG,"object-->null");
                    }
                }catch (Exception e){
                    Log.e(TAG,"Exception-->"+e.getMessage());
                    return null;
                }
                Log.e(TAG,"NULL");
                return null;
            }
        })
        .subscribeOn(Schedulers.io())
        .flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {

                return Observable.just(student).map(new Func1<Student, Course>() {
                    @Override
                    public Course call(Student student) {
                        return student.getCourseList().get(0);
                    }
                });
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError-->" + e.getMessage());
            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG, "student-->" + course.getCourseName() + "--" + course.getTeacherName());
            }
        });


        Observable.from(strings)
            .flatMap(new Func1<String, Observable<Course>>() {
                @Override
                public Observable<Course> call(String s) {
                    return null;
                }
            }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG, "STUDENT-->" + course.getCourseName());
            }
        });
    }

    private void testRxJava7(){
        final String[] strings = {"str1","str2","str3"};
        Observable.from(strings)
            .subscribeOn(Schedulers.io())
            .flatMap(new Func1<String, Observable<Student>>() {
                @Override
                public Observable<Student> call(final String s) {
                    return Observable.defer(new Func0<Observable<Student>>() {
                        @Override
                        public Observable call() {
                            try {
                                return Observable.just(getStudentEntity(s));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        }
                    });
                }
            })
            .subscribeOn(Schedulers.io())
            .flatMap(new Func1<Student, Observable<Course>>() {
                @Override
                public Observable<Course> call(final Student student) {
                    return Observable.just(student).map(new Func1<Student, Course>() {
                        @Override
                        public Course call(Student student) {
                            return student.getCourseList().get(0);
                        }
                    });
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Course>() {
                @Override
                public void onCompleted() {
                    Log.e(TAG, "testRxJava7--onCompleted--");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "testRxJava7--onError---" + e.getMessage());
                }

                @Override
                public void onNext(Course course) {
                    Log.e(TAG, "testRxJava7--onNext-->" + course.getCourseName() + "---" + course.getTeacherName());
                }
            });
    }

    private Observable<Student> getStudent(final String url){
        return Observable.defer(new Func0<Observable<Student>>() {
            @Override
            public Observable<Student> call() {
                try {
                    return Observable.just(getStudentEntity(url));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    return Observable.error(e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return Observable.error(e);
                }
            }
        });
    }

    private Student getStudentEntity(String url) throws ExecutionException, InterruptedException {
        String name = url;
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        url = "http://www.weather.com.cn/adat/sk/101010100.html";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, future, future);
        RxJavaApplcation.getInstance().getRequestQueue().add(req);
        JSONObject object = future.get();
        RxJavaApplcation.getInstance().getRequestQueue().add(req);
        if(object != null){
            Student student = new Student(name, 13);
            List<Course> courseList = new ArrayList<Course>();
            courseList.add(new Course(student.getName() + "-课程", "zhangsan"));
            student.setCourseList(courseList);
            return student;
        }
        return null;
    }


    /**
     * 主要是通过String对象获取到Student对象，再通过Student对象获取到Course对象，
     * 获取过程都是通过网络实现的 采用的时Volley
     * 可用在首先通过key获取到token，再通过token获取到下载文件的地址。
     */
    private void testRxJava8(){
        String[] str = {"dc7878","dc3547","dc1026"};
        Observable.from(str)
            .subscribeOn(Schedulers.io())
            .flatMap(new Func1<String, Observable<Student>>() {
                @Override
                public Observable<Student> call(final String s) {
                    return Observable.defer(new Func0<Observable<Student>>() {
                        @Override
                        public Observable<Student> call() {
                            try {
                                return Observable.just(getStudentEntity(s));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        }
                    });
                }
            })
            .subscribeOn(Schedulers.io())
            .flatMap(new Func1<Student, Observable<Course>>() {
                @Override
                public Observable<Course> call(final Student student) {
                    return Observable.defer(new Func0<Observable<Course>>() {
                        @Override
                        public Observable<Course> call() {
                            try {
                                return Observable.just(getCourseByStudent(student));
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        }
                    });
                }
            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Course>() {
                @Override
                public void onCompleted() {
                    Log.e(TAG, "testRxJava8--onCompleted--");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "testRxJava8--onError--" + e.getMessage());
                }

                @Override
                public void onNext(Course course) {
                    Log.e(TAG, "testRxJava8--onNext-->" + course.getCourseName() + "---" + course.getTeacherName());
                }
            });
    }

    private Course getCourseByStudent(Student student) throws Exception{
        if(student == null){
            throw new NullPointerException("student can not be null");
        }

        if(student.getCourseList() == null){
            throw new NullPointerException("student course can not be null");
        }

        if(student.getCourseList().size() == 0 ){
            throw new NullPointerException("student course size can not below zero");
        }
        return student.getCourseList().get(0);
    }

    private void testRxJava9(){
        final String[] strings = {"dc7878","dc3547","dc1026"};
        final String url = "http://www.weather.com.cn/adat/sk/101010100.html";
        final OkHttpClient okHttpClient = new OkHttpClient();
        Observable.from(strings)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Student>>() {
                    @Override
                    public Observable<Student> call(final String s) {
                        return Observable.defer(new Func0<Observable<Student>>() {
                            @Override
                            public Observable<Student> call() {
                                try {
                                    Response response = okHttpClient.newCall(new okhttp3.Request.Builder().url(url).build()).execute();
                                    if (!response.isSuccessful()) {
                                        return Observable.error(new Exception("error"));
                                    }
                                    Student student = new Student(s, 11);
                                    List<Course> courseList = new ArrayList<Course>();
                                    courseList.add(new Course(student.getName() + "-课程", "zhangsan"));
                                    student.setCourseList(courseList);
                                    return Observable.just(student);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    return Observable.error(e);
                                }
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.just(student.getCourseList().get(0));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Course>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG,"testRxJava--onCompleter-->");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"testRxJava9--onError-->"+e.getMessage());
                    }

                    @Override
                    public void onNext(Course course) {
                        Log.e(TAG,"testRxJava9-->"+course.getCourseName()+"--"+course.getTeacherName());
                    }
                });
    }

    private void testRxJava10(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        final String url = "http://www.weather.com.cn/adat/sk/101010100.html";
        Observable
            .create(new Observable.OnSubscribe<Student>() {
                @Override
                public void call(Subscriber<? super Student> subscriber) {
                    Response response;
                    try {
                        Log.e(TAG, "NET");
                        response = okHttpClient.newCall(new okhttp3.Request.Builder().url(url).build()).execute();
                        if (!response.isSuccessful()) {
                            Log.e(TAG, "NET--no -Successful");
                            subscriber.onError(new Exception("error"));
                        }
                        Log.e(TAG, "NET---Successful--");
                        subscriber.onNext(new Student("dc7878", 123));
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "ERROR--" + e.getMessage());
                        subscriber.onError(e);
                    }
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Student>() {
                @Override
                public void onCompleted() {
                    Log.e(TAG, "testRxJava10---onCompleted---");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "testRxJava10---onError---" + e.getMessage());
                }

                @Override
                public void onNext(Student student) {
                    tvInfo.setText(student.getName());
                    Log.e(TAG, "testRxJava10---onNext----" + student.getName() + "--" + student.getAge());
                }
            });

        Observable.just("111").create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    private void testRxJava11() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final String url = "https://www.google.com";
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Response response;
                try {
                    response = okHttpClient.newCall(new okhttp3.Request.Builder().url(url).build()).execute();
                    Log.e(TAG,"1111");
                } catch (IOException e) {
                    Log.e(TAG,"222");
                    e.printStackTrace();
                    subscriber.onError(e);
                    Log.e(TAG, "33333");
                    return;
                }
                Log.e(TAG,"4444");
                if(response.isSuccessful()){
                    Log.e(TAG,"5555");
                    if(!subscriber.isUnsubscribed()){
                        subscriber.onNext(response.body().toString()+"--"+ response.message());
                    }
                }else {
                    Log.e(TAG,"66666");
                    if(!subscriber.isUnsubscribed()){
                        subscriber.onError(new APIException(response.code(),response.message()));
                    }
                    Log.e(TAG,"77777");
                    return;
                }
                Log.e(TAG,"8888");
                if(!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
                Log.e(TAG,"9999");
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "testRxJava11-->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof APIException) {
                    APIException exception = (APIException) e;
                    Log.e(TAG, "testRxJava11-->onError-->" + exception.getMessage());
                } else if (e instanceof SocketTimeoutException) {
                    Log.e(TAG, "testRxJava11-->onError-->SocketTimeoutException");
                } else if (e instanceof ConnectException) {
                    Log.e(TAG, "testRxJava11-->onError-->ConnectException");
                } else{
                    Log.e(TAG, "testRxJava11-->onError--"+e.getMessage()+"--");
                }
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "testRxJava11-->onNext-->" + s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }
}
