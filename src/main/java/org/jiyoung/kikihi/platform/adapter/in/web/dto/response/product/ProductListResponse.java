package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductListResponse {

    private String categoryName;

    private String productName;

    private String brand;

    private int productPrice;

    private String productTitle;

    private String manufacturer;

    public static ProductListResponse from(Product entity) {
        ProductInfo info = entity.getInfo();

        return ProductListResponse.builder()
                .categoryName(info.getCategoryCode())
                .productName(info.getProductName())
                .productTitle(info.getProductTitle())
                .productPrice(info.getProductPrice())
                .brand(info.getBrand())
                .manufacturer(info.getManufacturer())
                .build();
    }

    public static List<ProductListResponse> from(List<Product> entities) {
       return entities.stream().map(ProductListResponse::from).toList();
    }

}
