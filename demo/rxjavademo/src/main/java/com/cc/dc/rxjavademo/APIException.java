package com.cc.dc.rxjavademo;

/**
 * Created by dc on 16/5/7.
 */
public class APIException extends Exception{

    private int code;
    private String message;
    public APIException(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
