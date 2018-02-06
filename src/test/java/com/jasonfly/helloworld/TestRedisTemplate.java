package com.jasonfly.helloworld;

import com.jasonfly.helloworld.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("neo", "ityouknow");
        Assert.assertEquals("ityouknow", redisTemplate.opsForValue().get("neo"));
    }

    @Test
    public void testObj() {
        User user = new User("ityouknow", 20, "123456");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.neo", user);
        User u = operations.get("com.neo");
        System.out.println("users: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user = new User("ityouknow@126.com", 12, "123456");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("expire");
        if (exists) {
            System.out.println("exist is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        redisTemplate.opsForValue().set("deletekey", "ityouknow");
        redisTemplate.delete("deletekey");
        boolean exists = redisTemplate.hasKey("deletekey");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash", "you", "you");
        String value = (String)hash.get("hash", "you");
        System.out.println("hash value: " + value);
    }

    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush("list", "it");
        list.leftPush("list", "you");
        list.leftPush("list", "know");
        String value = (String)list.leftPop("list");
        System.out.println("list value: " + value.toString());
    }

    @Test
    public void testSet() {
        String key = "set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, "it");
        set.add(key, "you");
        set.add(key, "you");
        Set<String> values = set.members(key);
        for (String v:values) {
            System.out.println("set value: " + v);
        }
    }

    @Test
    public void testZset() {
        String key = "zset";

        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key, "it", 1);
        zset.add(key, "you", 5);
        zset.add(key, "know", 4);

        Set<String> zsets = zset.range(key, 0, 3);
        for (String v:zsets) {
            System.out.println("zset value: " + v);
        }

        Set<String> zsetB = zset.rangeByScore(key, 0, 3);
        for (String v:zsetB) {
            System.out.println("zsetB value: " + v);
        }
    }

}
