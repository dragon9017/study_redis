package study_redis.redis.com.common.redis;
import javafx.beans.binding.ObjectExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @Auther lby
 * @Date 2020/9/15
 */
/**
 * redis 配置类
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> RedisConfiguration(RedisConnectionFactory  connectionFactory){
        RedisTemplate<String, Object> RedisTemplate = new RedisTemplate<>();
        /**
         * 自定义RedisTemplate
         * 配置具体的序列化
         */
        Jackson2JsonRedisSerializer<Object> JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        RedisTemplate.setKeySerializer(JsonRedisSerializer);
        return RedisTemplate;

    }
}
