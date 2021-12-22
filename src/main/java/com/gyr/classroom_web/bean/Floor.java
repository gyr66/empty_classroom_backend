package com.gyr.classroom_web.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Floor implements Serializable {
    private int floor;
    private List<Classroom> classroomList;
}
