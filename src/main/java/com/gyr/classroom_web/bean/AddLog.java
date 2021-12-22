package com.gyr.classroom_web.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AddLog {
    Date date;
    String ip;
    String classroomId;
}
