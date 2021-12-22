package com.gyr.classroom_web.controller;

import com.gyr.classroom_web.bean.Message;
import com.gyr.classroom_web.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/unableClassroom")
public class UnableClassroomController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisService redisService;

    @RequestMapping("/getAll")
    public Message getUnableClassroomList() {
        Message message = new Message();
        try {
            Set<String> unableClassroomList = redisService.getUnableClassroomList();
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
    public Message addUnableClassroomList(String id) {
        Message message = new Message();
        try {
            redisService.addUnavailableClassroom(id);
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
            flag = redisService.removeUnavailableClassroom(id);
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
}
