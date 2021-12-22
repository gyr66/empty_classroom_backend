package com.gyr.classroom_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service
public class RedisService {
    final String UNCERTAIN_CLASSROOM_KEY = "uncertainClassrooms";
    final String UNAVAILABLE_CLASSROOM_KEY = "unavailableClassrooms";

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, List<String>> redisTemplate;

    public List<String> getEmptyClassroomList(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void addUncertainClassroom(String id) {
        stringRedisTemplate.opsForSet().add(UNCERTAIN_CLASSROOM_KEY, id);
    }

    public void addUnavailableClassroom(String id) {
        stringRedisTemplate.opsForSet().add(UNAVAILABLE_CLASSROOM_KEY, id);
    }

    public boolean checkIsUncertain(String id) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(UNCERTAIN_CLASSROOM_KEY, id));
    }

    public boolean checkIsUnavailable(String id) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(UNAVAILABLE_CLASSROOM_KEY, id));
    }

    public Set<String> getUnableClassroomList() {
        return stringRedisTemplate.opsForSet().members(UNAVAILABLE_CLASSROOM_KEY);
    }

    public Set<String> getUncertainClassroomList() {
        return stringRedisTemplate.opsForSet().members(UNCERTAIN_CLASSROOM_KEY);
    }

    public Long removeUnavailableClassroom(String id) {
        return stringRedisTemplate.opsForSet().remove(UNAVAILABLE_CLASSROOM_KEY, id);
    }

    public Long removeUncertainClassroom(String id) {
        return stringRedisTemplate.opsForSet().remove(UNCERTAIN_CLASSROOM_KEY, id);
    }


}
