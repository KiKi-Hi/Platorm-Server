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
public class ProductStatisticsRedisHash {

    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer viewCount = 0;

    /// 생성자
    public static ProductStatisticsRedisHash of(ProductStatistics statistics) {
        return ProductStatisticsRedisHash.builder()
                .likeCount(statistics.getLikeCount())
                .commentCount(statistics.getCommentCount())
                .viewCount(statistics.getViewCount())
                .build();
    }

    ///  Domain
    public ProductStatistics toDomain(){
        return ProductStatistics.builder()
                .likeCount(this.likeCount)
                .commentCount(this.commentCount)
                .viewCount(this.viewCount)
                .build();
    }
}
