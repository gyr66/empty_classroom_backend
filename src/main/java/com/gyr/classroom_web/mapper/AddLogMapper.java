package com.gyr.classroom_web.mapper;

import com.gyr.classroom_web.bean.AddLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddLogMapper {
    List<AddLog> getAll();

    void insert(AddLog addLog);
}
