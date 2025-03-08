package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductTagResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("name")
    private String name;

    public static List<ProductTagResponse> from(List<Tag> tags) {
        return tags.stream()
                .map(tag -> ProductTagResponse.builder()
//                        .productId(tag.getProductId())
                        .name(tag.getName())
                        .build())
                .toList();
    }
}
