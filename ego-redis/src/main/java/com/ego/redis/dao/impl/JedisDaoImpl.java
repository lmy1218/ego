package com.ego.redis.dao.impl;

import com.ego.redis.dao.JedisDao;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Repository
public class JedisDaoImpl implements JedisDao {

    @Resource
    private JedisCluster jedisClients;


    public Boolean exists(String key) {
        return jedisClients.exists(key);
    }

    public Long del(String key) {
        return jedisClients.del(key);
    }

    public String set(String key, String value) {
        return jedisClients.set(key, value);
    }

    public String get(String key) {
        return jedisClients.get(key);
    }

    public Long expire(String key, int seconds) {
        return jedisClients.expire(key, seconds);
    }
}
