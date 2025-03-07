package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;

import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgRequest {

    private Long productId;
    private String thumbnailImage;
    private List<String> mainImages;
    private List<String> descriptionImages;
    private String descriptionHtml;  // HTML 설명

    public static ProductImgRequest from(ProductImg productImg) {
        return ProductImgRequest.builder()
                .thumbnailImage(productImg.getThumbnailImg())
                .mainImages(productImg.getMainImgs())
                .descriptionImages(productImg.getDescriptionImgs())
                .descriptionHtml(productImg.getDescriptionHtml())
                .build();
    }
}
