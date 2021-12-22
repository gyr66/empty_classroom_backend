package com.gyr.classroom_web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StatisticDB {
    final String TIMES_KEY = "times";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void incrTimes(int day) {
        stringRedisTemplate.opsForValue().increment(TIMES_KEY + day);
    }

    public int getTimes(int day) {
        return Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(TIMES_KEY + day)));
    }
}
