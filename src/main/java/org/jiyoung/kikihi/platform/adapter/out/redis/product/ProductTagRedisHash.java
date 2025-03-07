package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.ProductTag;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("ProductTag")
@Getter
@Builder
public class ProductTagRedisHash {

    private Long id;
    private Long tagId;
    private Long productId;

    // Constructor for easier creation of instances
    public static ProductTagRedisHash from(ProductTag productTag) {
        return ProductTagRedisHash.builder()
                .id(productTag.getId())
                .tagId(productTag.getTagId())
                .productId(productTag.getProductId())
                .build();
    }


}

