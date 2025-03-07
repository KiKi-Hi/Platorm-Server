package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.Data;

@Data
public class ProductInfoRequest {

    private Long productId;
    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice = 0;
    private String productTitle;
    private String brand;
    private String manufacturer;

}
