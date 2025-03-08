package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductInfo;

@Getter
@Builder
@Data
public class ProductInfoResponse {

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("product_price")
    private int productPrice;

    @JsonProperty("product_title")
    private String productTitle;

    @JsonProperty("manufacturer")
    private String manufacturer;

    /// from
    public static ProductInfoResponse from(ProductInfo productInfo) {

        return ProductInfoResponse.builder()
                .categoryId(productInfo.getCategoryCode())
                .productName(productInfo.getProductName())
                .brand(productInfo.getBrand())
                .productPrice(productInfo.getProductPrice())
                .productTitle(productInfo.getProductTitle())
                .manufacturer(productInfo.getManufacturer())
                .build();

    }
}
