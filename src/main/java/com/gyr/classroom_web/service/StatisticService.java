package com.gyr.classroom_web.service;

import com.gyr.classroom_web.redis.StatisticDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    StatisticDB statisticDB;

    public void dealVisit() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) day = 7;
        statisticDB.incrTimes(day);
    }

    public List<Integer> exportTimes() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            list.add(statisticDB.getTimes(i));
        }
        return list;
    }
}
