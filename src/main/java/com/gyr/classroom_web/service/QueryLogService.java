package com.gyr.classroom_web.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gyr.classroom_web.bean.QueryLog;
import com.gyr.classroom_web.mapper.QueryLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryLogService {
    @Autowired
    private QueryLogMapper queryLogMapper;

    public PageInfo<QueryLog> getAll(int page) {
        PageHelper.startPage(page, 5, "date desc");
        return new PageInfo<>(queryLogMapper.getAll());
    }

    public void insertLog(QueryLog queryLog) {
        queryLogMapper.insert(queryLog);
    }
}
