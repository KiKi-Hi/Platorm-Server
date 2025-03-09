package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "productImg", timeToLive = 3600) // 1시간 TTL
public class ProductImgRedisHash {

    @Id
    private Long id;  // 또는 해당 엔티티의 고유한 식별자 타입을 사용

    @Indexed
    private Long productId;

    private String thumbnailImg;
    private List<String> mainImgs;
    private List<String> descriptionImgs;
    private String descriptionHtml;

    // 생성자 메서드
//    public static ProductImgRedisHash of(ProductImg productImg) {
//        return ProductImgRedisHash.builder()
//                .productId(productImg.getProductId()) // productId 추가
//                .thumbnailImg(productImg.getThumbnailImg())
//                .mainImgs(productImg.getMainImgs())
//                .descriptionImgs(productImg.getDescriptionImgs())
//                .descriptionHtml(productImg.getDescriptionHtml())
//                .build();
//    }

    public static ProductImgRedisHash of(List<ProductImg> imgs) {
        return ProductImgRedisHash.builder()
                .productId(imgs.get(0).getProductId())
                .thumbnailImg(imgs.get(0).getThumbnailImg())
                .mainImgs(imgs.get(0).getMainImgs())
                .descriptionImgs(imgs.get(0).getDescriptionImgs())
                .descriptionHtml(imgs.get(0).getDescriptionHtml())
                .build();
    }

    public static ProductImgRedisHash from(ProductImg img) {
        return ProductImgRedisHash.builder()
                .productId(img.getProductId())
                .thumbnailImg(img.getThumbnailImg())
                .mainImgs(img.getMainImgs())
                .descriptionImgs(img.getDescriptionImgs())
                .descriptionHtml(img.getDescriptionHtml())
                .build();

    }


    public ProductImg toDomain() {
        return ProductImg.builder()
                .productId(productId)
                .thumbnailImg(thumbnailImg)
                .mainImgs(mainImgs)
                .descriptionImgs(descriptionImgs)
                .descriptionHtml(descriptionHtml)
                .build();
    }
}
