package com.gyr.classroom_web.bean;

import lombok.Data;

import java.util.Date;

@Data
public class QueryLog {
    Date date;
    String ip;
    Query query;
}
