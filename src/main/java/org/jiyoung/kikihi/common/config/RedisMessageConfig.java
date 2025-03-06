package org.jiyoung.kikihi.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;;
/*
*springboot에서 redis를 메시지 브로커로 활용
*메세지 발행
* 비동기 이벤트 기반 메시징을 가능하게 함
* */

@Configuration
public class RedisMessageConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

}
