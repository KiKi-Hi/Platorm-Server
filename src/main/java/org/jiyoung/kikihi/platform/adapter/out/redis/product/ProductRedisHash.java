package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;
import org.springframework.data.redis.core.RedisHash;

import java.beans.PropertyEditor;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "product", timeToLive = 14440)
public class ProductRedisHash implements Serializable {

    @Id
    private String userId;  // 혹은 Long id

    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private ProductSnippetRedisHash snippet;
    private ProductStatisticsRedisHash statistics;
    private ProductTagRedisHash tags;
    private ProductOptionRedisHash options;
    private ProductImg imgs;

}
