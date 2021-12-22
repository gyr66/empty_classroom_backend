package com.gyr.classroom_web.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gyr.classroom_web.bean.AddLog;
import com.gyr.classroom_web.mapper.AddLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddLogService {
    @Autowired
    private AddLogMapper addLogMapper;

    public PageInfo<AddLog> getAll(int page) {
        PageHelper.startPage(page, 5, "date desc");
        return new PageInfo<>(addLogMapper.getAll());
    }

    public void insertLog(AddLog addLog) {
        addLogMapper.insert(addLog);
    }
}