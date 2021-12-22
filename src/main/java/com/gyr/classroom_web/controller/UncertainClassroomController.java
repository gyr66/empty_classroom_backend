package com.gyr.classroom_web.controller;

import com.github.pagehelper.PageInfo;
import com.gyr.classroom_web.bean.AddLog;
import com.gyr.classroom_web.bean.Message;
import com.gyr.classroom_web.service.AddLogService;
import com.gyr.classroom_web.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/uncertainClassroom")
public class UncertainClassroomController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisService redisService;

    @Autowired
    AddLogService addLogService;

    @RequestMapping("/getAll")
    public Message getUncertainClassroomList() {
        Message message = new Message();
        try {
            Set<String> unableClassroomList = redisService.getUncertainClassroomList();
            message.setCode(200);
            message.setMsg("查询成功!");
            message.put("list", unableClassroomList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(300);
            message.setMsg("查询失败!");
        }
        return message;
    }

    @RequestMapping("/add")
    public Message addUncertainClassroomList(String id) {
        Message message = new Message();
        try {
            redisService.addUncertainClassroom(id);
            message.setCode(200);
            message.setMsg("添加成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(300);
            message.setMsg("添加失败!");
        }
        return message;
    }

    @RequestMapping("/remove")
    public Message removeUncertainClassroom(String id) {
        Message message = new Message();
        long flag = 0;
        try {
            flag = redisService.removeUncertainClassroom(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (flag > 0) {
            message.setCode(200);
            message.setMsg("删除成功!");
        } else {
            message.setCode(300);
            message.setMsg("删除失败!");
        }
        return message;
    }

    @RequestMapping("/getLog")
    public Message getAllAddLog(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageInfo<AddLog> info = addLogService.getAll(page);
        Message message = new Message();
        message.setCode(200);
        message.setMsg("查询成功!");
        message.put("info", info);
        return message;
    }
}
