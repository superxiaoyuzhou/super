package com.example.demo.dto;



import com.google.common.base.Strings;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求对象基类.
 * @author Administrator
 */
public class Request extends ConcurrentHashMap<String, Object>{

    public Request set(final String key, final Object value) {
        if (StringUtils.isEmpty(key)) {
            put(key, value != null ? value :"");
        }
        return this;
    }
    public String get(final String key){
        if (Strings.isNullOrEmpty(key) || !super.containsKey(key)){
            return null;
        }
        if (super.get(key) == null){
            return null;
        }
        return String.valueOf(super.get(key));
    }

    public Object getObject(final String key){
        if (Strings.isNullOrEmpty(key) || !super.containsKey(key)){
            return null;
        }
        if (super.get(key) == null){
            return null;
        }
        return super.get(key);
    }
}
