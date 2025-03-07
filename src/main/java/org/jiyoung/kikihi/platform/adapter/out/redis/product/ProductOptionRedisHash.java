package org.jiyoung.kikihi.platform.adapter.out.redis.product;


import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash("ProductOption")
public class ProductOptionRedisHash {

    private Long id;

    private Long productId;
    private String color;
    private String switchType;
    private String layout;
    private boolean isWireless;
    private int extraPrice;

    // from 도메인
    public static ProductOptionRedisHash from(ProductOption productOption) {
        return ProductOptionRedisHash.builder()
                .id(productOption.getId())
                .productId(productOption.getProductId())
                .color(productOption.getColor())
                .switchType(productOption.getSwitchType())
                .layout(productOption.getLayout())
                .isWireless(productOption.isWireless())
                .extraPrice(productOption.getExtraPrice())
                .build();
    }
}