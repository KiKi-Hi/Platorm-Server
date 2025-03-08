package org.jiyoung.kikihi.platform.adapter.out.redis.product;


import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash("ProductOption")
public class ProductOptionRedisHash {
    @Id
    private Long id;  // 또는 해당 엔티티의 고유한 식별자 타입을 사용

    @Indexed
    private Long productId;

    private String color;
    private String switchType;
    private String layout;
    private boolean isWireless;

    // from 도메인
    public static ProductOptionRedisHash of(ProductOption productOption) {
        return ProductOptionRedisHash.builder()
                .productId(productOption.getProductId())
                .color(productOption.getColor())
                .switchType(productOption.getSwitchType())
                .layout(productOption.getLayout())
                .isWireless(productOption.isWireless())
                .build();
    }


    public static ProductOptionRedisHash from(ProductOption productOption) {
        return ProductOptionRedisHash.builder()
                .productId(productOption.getProductId())
                .color(productOption.getColor())
                .switchType(productOption.getSwitchType())
                .layout(productOption.getLayout())
                .isWireless(productOption.isWireless())
                .build();
    }

    // toDomain
    public ProductOption toDomain() {
        return ProductOption.builder()
                .productId(productId)
                .color(color)
                .switchType(switchType)
                .layout(layout)
                .isWireless(isWireless)
                .build();
    }

    // 비즈니스 도메인
    public void changeProductId(Long productId) {
        this.productId = productId;
    }
}