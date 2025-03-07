package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.Data;

@Data
public class ProductInfoRequest {

    private String productName;
    private String description;
    private String categoryCode;
    private int productPrice ;
    private String productTitle;
    private String brand;
    private String manufacturer;

}
