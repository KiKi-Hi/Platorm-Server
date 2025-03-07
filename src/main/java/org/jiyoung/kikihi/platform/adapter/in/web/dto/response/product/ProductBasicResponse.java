package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;

@Getter
@Builder
@Data
public class ProductBasicResponse {

    private String categoryId;

    private String productName;

    private String brand;

    private int productPrice;

    private String productTitle;

    private String manufacturer;

    /// from
    public static ProductBasicResponse from(Product product) {

        ProductInfo info = product.getInfo();

        return ProductBasicResponse.builder()
                .categoryId(info.getCategoryCode())
                .productName(info.getProductName())
                .brand(info.getBrand())
                .productPrice(info.getProductPrice())
                .productTitle(info.getProductTitle()) // productTitle 필드가 Product 엔티티에 있어야 함
                .manufacturer(info.getManufacturer())
                .build();
    }


}
