package org.jiyoung.kikihi.platform.adapter.out.redis.product;


import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "productInfo", timeToLive = 3600) // 1시간 TTL
public class ProductInfoRedisHash implements Serializable {

    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private String productTitle;
    private String brand;
    private String manufacturer;

    /// 생성자
    public static ProductInfoRedisHash of(ProductInfo info) {

        return ProductInfoRedisHash.builder()
                .productName(info.getProductName())
                .productTitle(info.getProductTitle())
                .description(info.getDescription())
                .productPrice(info.getProductPrice())
                .categoryCode(info.getCategoryCode())
                .brand(info.getBrand())
                .manufacturer(info.getManufacturer())
                .build();
    }

    /// ToDomain
    public ProductInfo toDomain(){
        return ProductInfo.builder()
                .productName(productName)
                .productTitle(productTitle)
                .description(description)
                .productPrice(productPrice)
                .categoryCode(categoryCode)
                .brand(brand)
                .manufacturer(manufacturer)
                .build();

    }
}
