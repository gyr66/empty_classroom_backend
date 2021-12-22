package com.gyr.classroom_web.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Query implements Serializable {
    private Integer startWeek;
    private Integer endWeek;
    private Integer day;
    private Integer building;
    private List<Integer> intervals;
}
