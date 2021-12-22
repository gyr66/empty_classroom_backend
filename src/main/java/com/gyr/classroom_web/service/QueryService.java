package com.gyr.classroom_web.service;

import com.gyr.classroom_web.bean.Classroom;
import com.gyr.classroom_web.bean.Floor;
import com.gyr.classroom_web.bean.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QueryService {
    @Autowired
    RedisService redisService;


    private List<String> queryEmptyClassRoomList(Query query) {
        String key = this.keyBuilder(query);
        return this.redisService.getEmptyClassroomList(key);
    }

    public List<Floor> queryEmptyClassRoom(Query query) {
        // 判断classroom是free还是crowded
        Set<String> set = new HashSet<>();
        List<Integer> intervals = query.getIntervals();
        int firstInterval = intervals.get(0);
        // 查询上一个时段的空教室情况
        int lastInterval = firstInterval - 2;
        if (lastInterval > 0) {
            Query assistantQuery = new Query();
            List<Integer> assistantIntervals = new ArrayList<>();
            assistantIntervals.add(lastInterval);
            assistantQuery.setBuilding(query.getBuilding());
            assistantQuery.setStartWeek(query.getStartWeek());
            assistantQuery.setEndWeek(query.getEndWeek());
            assistantQuery.setDay(query.getDay());
            assistantQuery.setIntervals(assistantIntervals);
            set.addAll(queryEmptyClassRoomList(assistantQuery));
        }
        List<String> classroomList = queryEmptyClassRoomList(query);
        List<Floor> floorList = new ArrayList<>();
        int curFloor = 0;
        if (classroomList == null) return floorList;
        for (String id : classroomList) {
            int building = Integer.parseInt(id.substring(0, 1));
            // 判断是否在所查询的教学楼中
            if (building != query.getBuilding()) continue;
            // 得到当前教室
            Classroom classroom = new Classroom();
            classroom.setId(id);
            if (set.contains(id))
                classroom.setType(Classroom.TYPE.CROWDED); // 上一个时段也是空闲的，当前时段可能拥挤
            else classroom.setType(Classroom.TYPE.FREE); // 上一个时段有课，当前时段人较少
            if (redisService.checkIsUncertain(id))
                classroom.setType(Classroom.TYPE.UNCERTAIN); // 属于数据不准确的教室
            if (redisService.checkIsUnavailable(id))
                classroom.setType(Classroom.TYPE.UNAVAILABLE); // 属于考研专用教室
            // 得到当前楼层
            int floor = Integer.parseInt(id.substring(1, 2));
            // 说明是一个新楼层，新建一个楼层，并加入到返回列表中
            if (floor > curFloor) {
                curFloor = floor;
                Floor newFloor = new Floor();
                newFloor.setFloor(floor);
                List<Classroom> classrooms = new ArrayList<>();
                newFloor.setClassroomList(classrooms);
                floorList.add(newFloor);
            }
            // 将当前教室加入到对应楼层
            for (Floor item : floorList) {
                if (item.getFloor() == floor) {
                    item.getClassroomList().add(classroom);
                }
            }
        }
        return floorList;
    }

    private String keyBuilder(Query query) {
        StringBuilder url = new StringBuilder();
        String startWeek = "zcStart=" + query.getStartWeek();
        String endWeek = "zcEnd=" + query.getEndWeek();
        String xq = "xq=" + query.getDay();
        url.append(startWeek).append("&").append(endWeek).append("&").append(xq);
        List<Integer> intervals = query.getIntervals();
        for (Integer interval : intervals) {
            url.append("&sd[]=").append(interval);
        }
        return url.toString();
    }


}
