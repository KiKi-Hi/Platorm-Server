package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "product", timeToLive = 14440)
public class ProductRedisHash {

    @Id
    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private ProductSnippetRedisHash snippet;
    private ProductStatisticsRedisHash statistics;

}
