package com.cookabuy.util;

import java.util.HashMap;
import java.util.Map;

//统一定义的数据返回类

/**
 * error 错误信息
 * result 响应信息   一般为 fail和success
 * data  响应数据
 */
public class Result {
    private Map<String,Object> data;
    private String error;
    private String result;

    public Result(){
        result = ResponseType.SUCCESS.name();
    }

    public Result(String error) {
        this.error = error;
        result = ResponseType.FAIL.name();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object value){
        if(key != null){
            data.put(key,value);
        }
    }
    public void delData(String key){
        if(key != null){
            data.remove(key);
        }
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.result = ResponseType.FAIL.name();
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public enum ResponseType{
        FAIL,SUCCESS
    }
}
