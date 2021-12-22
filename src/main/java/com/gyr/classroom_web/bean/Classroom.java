package com.gyr.classroom_web.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Classroom implements Serializable {
    TYPE type;
    private String id;
    public enum TYPE {
        FREE, CROWDED, UNAVAILABLE, UNCERTAIN
    }
}
