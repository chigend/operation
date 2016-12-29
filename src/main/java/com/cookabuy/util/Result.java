package com.cookabuy.util;

import com.cookabuy.util.function.Consumer;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.text.StrBuilder;

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
    private ResponseType result;

    public Result(){
        data = new HashMap<>();
        result = ResponseType.SUCCESS;
    }

    public Result(String error) {
        this.error = error;
        result = ResponseType.FAIL;
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
    public void removeData(String key){
        if(key != null){
            data.remove(key);
        }
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.result = ResponseType.FAIL;
        this.error = error;
    }


    public boolean isSuccess() {
        return result.equals(ResponseType.SUCCESS);
    }

    public void ifSuccess(Consumer consumer){
        if (result.equals(ResponseType.SUCCESS)){
            consumer.consume();
        }
    }
    public enum ResponseType{
        FAIL,SUCCESS;
    }
}
