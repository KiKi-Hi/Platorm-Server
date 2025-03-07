package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStatistics {

    // embedded (0) vs 연관관계 (X)
    private Long productId;
    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer viewCount = 0;

    /// 생성자
    public static ProductStatistics of() {
        return ProductStatistics.builder()
                .likeCount(0)
                .commentCount(0)
                .viewCount(0)
                .build();
    }
}
