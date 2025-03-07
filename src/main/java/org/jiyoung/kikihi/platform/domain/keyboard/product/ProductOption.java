package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductOptionRequest;

@Getter
@Builder
@AllArgsConstructor
public class ProductOption {

    // embedded (X) vs 연관관계 (0)
    private Long id;
    private Long productId;// manytoone의 joincolumn했을때의 들어가는 id와 유사
    private String color;
    private String switchType;
    private String layout;
    private boolean isWireless;
    private int extraPrice; // 옵션 추가 가격

    ///  생성자
    public static ProductOption of(Long productId, String color, String switchType, String layout, boolean isWireless, int extraPrice) {
        return ProductOption.builder()
                .productId(productId)
                .color(color)
                .switchType(switchType)
                .layout(layout)
                .isWireless(isWireless)
                .extraPrice(extraPrice)
                .build();
    }
    public static ProductOption of(ProductOptionRequest productOption){
        return ProductOption.builder()
                .productId(productOption.getProductId())
                .color(productOption.getColor())
                .switchType(productOption.getSwitchType())
                .layout(productOption.getLayout())
                .isWireless(productOption.isWireless())
                .extraPrice(productOption.getExtraPrice())
                .build();

    }
}
