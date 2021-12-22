package com.gyr.classroom_web.controller;

import com.github.pagehelper.PageInfo;
import com.gyr.classroom_web.bean.Message;
import com.gyr.classroom_web.bean.QueryLog;
import com.gyr.classroom_web.service.QueryLogService;
import com.gyr.classroom_web.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queryNote")
@CrossOrigin
public class QueryNoteController {
    @Autowired
    QueryLogService queryLogService;

    @Autowired
    StatisticService statisticService;

    @RequestMapping("/getAll")
    public Message getNoteList(@RequestParam(value = "page", defaultValue = "1") int page) {
        Message message = new Message();
        PageInfo<QueryLog> info = queryLogService.getAll(page);
        message.setCode(200);
        message.setMsg("查询成功!");
        message.put("info", info);
        return message;
    }

    @RequestMapping("/getTimes")
    public Message getTimes() {
        Message message = new Message();
        message.setCode(200);
        message.setMsg("查询成功!");
        message.put("data", statisticService.exportTimes());
        return message;
    }
}
