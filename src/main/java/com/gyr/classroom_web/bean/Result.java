package com.gyr.classroom_web.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Result implements Serializable {
    private List<Floor> floorList;
}
