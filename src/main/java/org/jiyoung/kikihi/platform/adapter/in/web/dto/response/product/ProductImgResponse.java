package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductImgResponse {
    
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("thumbnail_image")
    private String thumbnailImg;

    @JsonProperty("main_images")
    private List<String> mainImgs;

    @JsonProperty("description_images")
    private List<String> descriptionImgs;

    @JsonProperty("description_html")
    private String descriptionHtml;

    // ProductImg 객체를 ProductImgResponse로 변환하는 메소드
    public static ProductImgResponse from(ProductImg productImg) {
        return ProductImgResponse.builder()
                .productId(productImg.getProductId())
                .thumbnailImg(productImg.getThumbnailImg())
                .mainImgs(productImg.getMainImgs())
                .descriptionImgs(productImg.getDescriptionImgs())
                .descriptionHtml(productImg.getDescriptionHtml())
                .build();
    }

    // ProductImg 리스트를 ProductImgResponse 리스트로 변환하는 메소드
    public static List<ProductImgResponse> from(List<ProductImg> productImgs) {
        return productImgs.stream()
                .map(ProductImgResponse::from)
                .toList();
    }
}
