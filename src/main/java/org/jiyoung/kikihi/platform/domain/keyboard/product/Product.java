package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.BaseDomain;

import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseDomain {

    private Long id;

    /// Embedded 관련 내용
    private ProductInfo info;
    private ProductStatistics statistics;

    // 누가 등록했는지 알아야함.
    private Long userId;


    /// 연관 관계 관련 내용
    /// 연관 관계 내용은 여러개가 가능하니 @OneToMany의 형식과 같다.
    /// 해당하는 것을 이용할시, 양방향의 의존관계가 필요함.
    /// ManyToOne은 꼭 엮어줘야함
    ///     단점 : 귀찮음, 추가적으로 조회 필요
    /// OneToMany는 요구사항에 따라서 다름. 거의 안맺는 추세
    ///     단점 : 생성부터 관계를 엮어줘야하기때문에 더 복잡.
    ///     단점 : 성능 속도가 느림.
    /// 맺게되면 양방향 의존관계를 맺어줘야하기 때문에.

    // 생성자
    public static Product of(ProductRequest request) {
        // 기본 정보 생성
        return Product.builder()
                .info(ProductInfo.of(request.getInfo()))
                .statistics(ProductStatistics.of())
                .userId(request.getUserId())
                .build();
    }

    // ! ---

    // 출력을 위한 저장
    // !! 생성에서 사용하지 않음 !!
    private List<ProductOption> options;

    public void changeOptions(List<ProductOption> options) {
        this.options = options;
    }


}
