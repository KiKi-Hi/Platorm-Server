package org.jiyoung.kikihi.platform.domain.keyboard.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.TagRequest;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {


    private Long id;
    private Long productId;
    private String name;

    ///  생성자
    public static Tag of(Long id, String name) {
        return Tag.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static Tag of(TagRequest tag) {
        return Tag.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public void changeProductId(Long id) {
        this.productId = id;
    }
}
