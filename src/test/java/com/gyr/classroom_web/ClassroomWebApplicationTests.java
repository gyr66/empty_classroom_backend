package com.gyr.classroom_web;

import com.github.pagehelper.PageInfo;
import com.gyr.classroom_web.bean.AddLog;
import com.gyr.classroom_web.bean.Query;
import com.gyr.classroom_web.bean.QueryLog;
import com.gyr.classroom_web.mapper.QueryLogMapper;
import com.gyr.classroom_web.service.AddLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ClassroomWebApplicationTests {

    @Autowired
    AddLogService addLogService;
    @Autowired
    QueryLogMapper queryLogMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void AddLogServiceGetAllTest() {
        PageInfo<AddLog> info = addLogService.getAll(1);
        List<AddLog> addLogList = info.getList();
        long total = info.getTotal();
        System.out.println(total);
        for (AddLog log : addLogList) {
            System.out.println(log);
        }
    }

    @Test
    void AddLogServiceInsertTest() {
        AddLog addLog = new AddLog();
        addLog.setDate(new Date());
        addLog.setIp("192.168.255.255");
        addLog.setClassroomId("4404");
        addLogService.insertLog(addLog);
    }

    @Test
    void QueryLogServiceInsertTest() {
        QueryLog queryLog = new QueryLog();
        queryLog.setDate(new Date());
        queryLog.setIp("192.168.192.178");
        Query query = new Query();
        query.setStartWeek(8);
        query.setEndWeek(8);
        query.setDay(6);
        query.setBuilding(4);
        List<Integer> intervals = new ArrayList<>();
        intervals.add(2);
        intervals.add(4);
        query.setIntervals(intervals);
        queryLog.setQuery(query);
        queryLogMapper.insert(queryLog);
    }

    @Test
    void QueryLogServiceGetAllTest() {
        List<QueryLog> queryLogList = queryLogMapper.getAll();
        for (QueryLog log : queryLogList) {
            System.out.println(log);
        }
    }

    @Test
    void CalendarTest() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        System.out.println(day);
    }
}
