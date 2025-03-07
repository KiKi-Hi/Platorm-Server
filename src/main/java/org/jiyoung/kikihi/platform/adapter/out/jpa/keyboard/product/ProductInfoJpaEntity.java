package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;


@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductInfoJpaEntity {

    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private String productTitle;
    private String brand;
    private String manufacturer;


    public static ProductInfoJpaEntity from(ProductInfo domain) {
        return ProductInfoJpaEntity.builder()
                .productTitle(domain.getProductTitle())
                .brand(domain.getBrand())
                .manufacturer(domain.getManufacturer())
                .build();
    }

    public ProductInfo toDomain() {
        return ProductInfo.builder()
                .productTitle(productTitle)
                .brand(brand)
                .manufacturer(manufacturer)
                .build();
    }


}
