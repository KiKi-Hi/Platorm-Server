package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "product", timeToLive = 14440)
public class ProductRedisHash{

    @Id
    private Long id;

    @Indexed /// 임시 저장을 위한 Indexed
    private Long userId;

    private ProductInfoRedisHash info;
    private ProductStatisticsRedisHash statistics;

    /// 생성자 , 도메인에서 레디스 해쉬를 만들어야 합니다.
    public static ProductRedisHash of(Product product) {

        return ProductRedisHash.builder()
                .id(product.getId())
                .userId(product.getUserId())
                .info(ProductInfoRedisHash.of(product.getInfo()))
                .statistics(ProductStatisticsRedisHash.of(product.getStatistics()))
                .build();
    }

    /// 도메인으로
    public Product toDomain(){
        return Product.builder()
                .id(id)
                .userId(userId)
                .info(info.toDomain())
                .statistics(statistics.toDomain())
                .build();
    }

}
