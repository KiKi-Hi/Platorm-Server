package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.BaseDomain;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.ProductTag;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseDomain {

    private Long id;

    private ProductInfo info;
    private ProductStatistics statistics;
    private ProductOption options;
    private ProductImg imgs;
    private List<ProductTag> tags = new ArrayList<>();

    // 생성자
    public static Product from(ProductRequest request) {
        // 기본 정보 생성
        Product product = Product.builder()
                .info(ProductInfo.of(request.getInfo()))
                .statistics(ProductStatistics.of())
                .options(request.getOptions())
                .tags(request.getTags())
                .build();
        return product;
    }

    public void addTag(Tag tag) {
        ProductTag productTag = ProductTag.of(this, tag);
        tags.add(productTag);
    }

}
