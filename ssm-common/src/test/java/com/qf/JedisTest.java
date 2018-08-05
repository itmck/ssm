//package com.qf;
//
//import org.junit.Test;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//import java.io.IOException;
//import java.util.HashSet;
//
///**
// * Create by it_mck 2018/7/28 12:00
// *
// * @Description:
// * @Version: 1.0
// */
//public class JedisTest {
//
//    @Test
//    public void test1() {
//
//        //创建jedis实例
//        Jedis jedis = new Jedis("47.96.123.213", 6379);
//        //设置密码
//        jedis.auth("mckmck$$");
//        System.out.println(jedis.get("name"));
//        jedis.close();
//
//    }
//
//    @Test
//    public void test2() {
//        //相当于先创建获得一个jedis的池子
//        JedisPool jedisPool = new JedisPool("47.96.123.213", 6379);//ctrl+p查看当前参数信息
//        //从池子里获取一个jedis实例
//        Jedis jedis = jedisPool.getResource();
//        jedis.auth("mckmck$$");
//        System.out.println(jedis.get("name"));
//        jedis.close();
//        jedisPool.close();
//    }
//
//    @Test
//    public void test3() throws IOException {
//
//        //创建集群集合
//        HashSet<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort("47.96.123.213",9001));
//        nodes.add(new HostAndPort("47.96.123.213",9002));
//        nodes.add(new HostAndPort("47.96.123.213",9003));
//        nodes.add(new HostAndPort("47.96.123.213",9004));
//        nodes.add(new HostAndPort("47.96.123.213",9005));
//        nodes.add(new HostAndPort("47.96.123.213",9006));
//        //获取jedisCluster对象
//        JedisCluster jedisCluster = new JedisCluster(nodes);
//        System.out.println(jedisCluster.get("name"));
//        jedisCluster.close();
//
//    }
//}
