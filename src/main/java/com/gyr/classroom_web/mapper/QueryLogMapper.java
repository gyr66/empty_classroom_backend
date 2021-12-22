package com.gyr.classroom_web.mapper;

import com.gyr.classroom_web.bean.QueryLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueryLogMapper {
    List<QueryLog> getAll();

    void insert(QueryLog addLog);
}
