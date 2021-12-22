package com.gyr.classroom_web.controller;

import com.gyr.classroom_web.bean.*;
import com.gyr.classroom_web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class Controller {
    @Autowired
    QueryService queryService;

    @Autowired
    RedisService redisService;

    @Autowired
    AddLogService addLogService;

    @Autowired
    QueryLogService queryLogService;

    @Autowired
    StatisticService statisticService;


    @RequestMapping("/emptyClassroom")
    public List<Floor> getEmptyClassroom(@RequestBody Query query, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        statisticService.dealVisit();
        QueryLog queryLog = new QueryLog();
        queryLog.setIp(remoteAddr);
        queryLog.setDate(new Date());
        queryLog.setQuery(query);
        queryLogService.insertLog(queryLog);

        return queryService.queryEmptyClassRoom(query);
    }

    @RequestMapping("/dealMistakeSubmit")
    public Message dealMistakeSubmit(String classroomId, String submitCode, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        Message message = new Message();

        if (!remoteAddr.equals("123.147.251.86") && !submitCode.equals("cquptjwzx")) {
            message.setCode(403);
            message.setMsg("提交码错误!");
            return message;
        }

        AddLog addLog = new AddLog();
        addLog.setDate(new Date());
        addLog.setIp(remoteAddr);
        addLog.setClassroomId(classroomId);
        addLogService.insertLog(addLog);

        redisService.addUncertainClassroom(classroomId);
        message.setCode(200);
        message.setMsg("提交成功!");
        return message;
    }
}
