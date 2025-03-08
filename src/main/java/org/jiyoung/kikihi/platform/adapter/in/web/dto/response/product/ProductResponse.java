package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {

    private ProductInfoResponse info;

    private List<ProductOptionResponse> option;

    private List<ProductImgResponse> img;

    private List<ProductTagResponse> tag;


    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .info(ProductInfoResponse.from(product.getInfo()))
                .option(ProductOptionResponse.from(product.getOptions()))
                .img(ProductImgResponse.from(product.getImgs()))
                .tag(ProductTagResponse.from(product.getTags()))
                .build();
    }


//    public static List<ProductResponse> from(List<Product> product) {
//
//        return product.stream()
//                .map(ProductResponse::from)
//                .toList();
//    }

    
}
