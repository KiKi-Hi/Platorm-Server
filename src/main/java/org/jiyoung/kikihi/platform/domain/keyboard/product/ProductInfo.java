package org.jiyoung.kikihi.platform.domain.keyboard.product;


import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductInfoRequest;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductInfo {

    // embedded (0) vs 연관관계 (X)
    private Long productId;
    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private String productTitle;
    private String brand;
    private String manufacturer;

    //생성자 도메인만드는 거-> of
    public static ProductInfo of(ProductInfoRequest productInfo) {
        return ProductInfo.builder()
                .productName(productInfo.getProductName())
                .description(productInfo.getDescription())
                .categoryCode(productInfo.getCategoryCode())
                .productPrice(productInfo.getProductPrice())
                .productTitle(productInfo.getProductTitle())
                .brand(productInfo.getBrand())
                .manufacturer(productInfo.getManufacturer())
                .build();

    }
}
