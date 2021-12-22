package com.gyr.classroom_web.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Message {
    int code;
    String msg;
    Map<String, Object> content = new HashMap<>();

    public void put(String key, Object value) {
        content.put(key, value);
    }
}
