package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductImgRequest;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductImg {

    // embedded (X) vs 연관관계 (0)
    private Long id;
    private Long productId;
    private String thumbnailImg;
    private List<String> mainImgs;
    private List<String> descriptionImgs;
    private String descriptionHtml;

    /// 생성자
    public static ProductImg of(ProductImgRequest request) {
        return ProductImg.builder()
                .productId(request.getProductId())
                .thumbnailImg(request.getThumbnailImage())
                .mainImgs(List.copyOf(request.getMainImages()))  // 불변 리스트
                .descriptionImgs(List.copyOf(request.getDescriptionImages()))
                .descriptionHtml(request.getDescriptionHtml())
                .build();
    }

    /// LIST 생성자, 기존의 of생성자를 활용하여 만들었다.
    public static List<ProductImg> of(List<ProductImgRequest> requests) {
        return requests.stream()
                .map(ProductImg::of).toList();
    }

    /// 값 비교를 위한 equals & hashCode 오버라이딩
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImg that = (ProductImg) o;
        return Objects.equals(thumbnailImg, that.thumbnailImg) &&
                Objects.equals(mainImgs, that.mainImgs) &&
                Objects.equals(descriptionImgs, that.descriptionImgs) &&
                Objects.equals(descriptionHtml, that.descriptionHtml);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnailImg, mainImgs, descriptionImgs, descriptionHtml);
    }
}
