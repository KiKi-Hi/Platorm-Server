package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductStatistics;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "productStatistics", timeToLive = 86400) // 1일 TTL
public class ProductStatisticsRedisHash implements Serializable {

    @Id
    private String productId; // Redis에서는 String key가 일반적

    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer viewCount = 0;

    /// 생성자
    public static ProductStatistics of(int likeCount, int commentCount, int viewCount) {
        return ProductStatistics.builder()
                .likeCount(likeCount)
                .commentCount(commentCount)
                .viewCount(viewCount)
                .build();
    }
}
