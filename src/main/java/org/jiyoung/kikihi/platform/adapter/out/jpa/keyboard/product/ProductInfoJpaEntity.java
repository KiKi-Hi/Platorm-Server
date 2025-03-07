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
    private int productPrice;
    private String productTitle;
    private String brand;
    private String manufacturer;


    public static ProductInfoJpaEntity from(ProductInfo domain) {
        return ProductInfoJpaEntity.builder()
                .productTitle(domain.getProductTitle())
                .brand(domain.getBrand())
                .manufacturer(domain.getManufacturer())
                .description(domain.getDescription())
                .productPrice(domain.getProductPrice())
                .productTitle(domain.getProductTitle())
                .productName(domain.getProductName())
                .categoryCode(domain.getCategoryCode())
                .productPrice(domain.getProductPrice())
                .build();

    }

    public ProductInfo toDomain() {
        return ProductInfo.builder()
                .productTitle(productTitle)
                .brand(brand)
                .manufacturer(manufacturer)
                .description(description)
                .productPrice(productPrice)
                .productName(productName)
                .categoryCode(categoryCode)
                .build();
    }


}
