package study_redis.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import study_redis.redis.com.api.entry.SysUser;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedisApplicationTests {
//    注入RedisTemplate
//    @Resource
//    private RedisTemplate redisTemplate;
//    使用自定义的redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        Map<String,Object> map=new HashMap<>();
        map.put("name","aaa");
//        操作字符串
        redisTemplate.opsForValue().set("name","aaa");
//        操作队列
        redisTemplate.opsForList().rightPush("name","aaa");
//        操作集合
        redisTemplate.opsForSet().add("name","aaa");
//        操作hash
        redisTemplate.opsForHash().putAll("name",map);
//        操作地图
//        redisTemplate.opsForGeo().add("beijing",114,15);
//        操作事务
//        开启事务
        redisTemplate.multi();
//        执行事务
        redisTemplate.exec();
//        关闭事务
        redisTemplate.discard();
//        操作数据库
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        connectionFactory.getConnection().flushAll();
    }

//    测试实体类序列化
    @Test
    void test01() throws JsonProcessingException {
        SysUser sysUser=new SysUser();
        sysUser.setAge(12);
        sysUser.setName("中国人");
        sysUser.setSex(true);
//        存入对象需要序列化
        String UserJson = new ObjectMapper().writeValueAsString(sysUser);
        redisTemplate.opsForValue().set("SysUser",UserJson);
        redisTemplate.opsForValue().get("SysUser");
    }

}
