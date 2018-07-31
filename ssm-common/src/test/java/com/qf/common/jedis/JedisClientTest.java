package com.qf.common.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create by it_mck 2018/7/29 19:08
 *
 * @Description:
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-jedis-test.xml")
public class JedisClientTest {
    @Autowired
    private JedisClientCluster jedisClientCluster;

    @Test
    public void get() {

        String name = jedisClientCluster.get("name");
        System.out.println(name);

    }
}