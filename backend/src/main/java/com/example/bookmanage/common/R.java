package com.example.bookmanage.common;

public class R<T> {
    private Integer code;
    private String message;
    private T data;
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
      public static <T> R<T> success() {
        R<T> r = new R<>();
        r.code = 200;
        return r;
    }
    
    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.code = 200;
        r.data = data;
        return r;
    }
    
    public static <T> R<T> success(String message) {
        R<T> r = new R<>();
        r.code = 200;
        r.message = message;
        return r;
    }
    
    public static <T> R<T> success(T data, String message) {
        R<T> r = new R<>();
        r.code = 200;
        r.data = data;
        r.message = message;
        return r;
    }
      public static <T> R<T> error() {
        R<T> r = new R<>();
        r.code = 500;
        return r;
    }
    
    public static <T> R<T> error(String message) {
        R<T> r = new R<>();
        r.code = 500;
        r.message = message;
        return r;
    }
    
    public static <T> R<T> error(Integer code, String message) {
        R<T> r = new R<>();
        r.code = code;
        r.message = message;
        return r;
    }
}