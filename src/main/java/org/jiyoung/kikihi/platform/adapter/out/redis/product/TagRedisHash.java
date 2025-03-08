package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.TagRequest;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.ProductTag;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@RedisHash("Tag")
@Getter
@Builder
public class TagRedisHash {
    @Id
    private Long id;  // 이 필드는 고유 식별자로 사용됩니다.

    @Indexed
    private Long productId;
    private String name;

    public static TagRedisHash of(Tag Tag, ProductTag productTag) {
        return TagRedisHash.builder()
                .productId(productTag.getProductId())
                .name(Tag.getName())
                .build();
    }

    public static TagRedisHash from(Tag tags) {
        return TagRedisHash.builder()
                .name(tags.getName())
                .build();
    }


    public Tag toDomain() {
        return Tag.builder()
                .name(name)
                .build();

    }
}

