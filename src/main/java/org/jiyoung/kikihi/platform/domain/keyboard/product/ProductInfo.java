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
    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice;
    private String productTitle;
    private String brand;
    private String manufacturer;

    //생성자 도메인만드는 거-> of
    public static ProductInfo of(ProductInfoRequest request) {
        return ProductInfo.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .categoryCode(request.getCategoryCode())
                .productPrice(request.getProductPrice())
                .productTitle(request.getProductTitle())
                .brand(request.getBrand())
                .manufacturer(request.getManufacturer())
                .build();
    }


}
