package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;
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



    /// 팩토리 메서드 (DTO → 도메인 변환)
    public static ProductImg from(ProductImg productImg) {
        return ProductImg.builder()
                .productId(productImg.getProductId())
                .thumbnailImg(productImg.getThumbnailImg())
                .mainImgs(List.copyOf(productImg.getMainImgs()))  // 불변 리스트
                .descriptionImgs(List.copyOf(productImg.getDescriptionImgs()))
                .descriptionHtml(productImg.getDescriptionHtml())
                .build();
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
