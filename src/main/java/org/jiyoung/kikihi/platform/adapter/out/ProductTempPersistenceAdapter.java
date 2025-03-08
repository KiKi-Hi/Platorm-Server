package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductImgRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.TagRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.repository.ProductImgRedisRepository;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.repository.ProductOptionRedisRepository;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.repository.ProductRedisRepository;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.repository.ProductTagRedisRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.TempProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductImg;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductTempPersistenceAdapter implements TempProductPort {

    /*
        임시 저장은 레디스를 통해서 처리한다.
     */

    private final ProductRedisRepository redisRepository;
    private final ProductOptionRedisRepository optionRedisRepository;
    private final ProductImgRedisRepository imgRedisRepository;
    private final ProductTagRedisRepository tagRedisRepository;
    private final ProductImgRedisRepository productImgRedisRepository;


    /// 저장 기능

    // Redis에 상품 임시 저장하기
    @Override
    public void saveTemporaryProduct(ProductRedisHash product) {
        redisRepository.save(product);
    }

    // Redis에 옵션 임시 저장하기
    @Override
    public void saveTemporalOptions(ProductOption options) {
        optionRedisRepository.save(ProductOptionRedisHash.from(options));
    }
    // Redis에 이미지 임시 저장하기
    @Override
    public void saveTemporalTag(Tag tags) {
        tagRedisRepository.save(TagRedisHash.from(tags));
    }

    // Redis에 이미지 임시 저장하기
    @Override
    public void saveTemporalImg(ProductImg imgs) {
        productImgRedisRepository.save(ProductImgRedisHash.from(imgs));

    }



    // Redis에 특정 사용자의 임시 저장된 상품을 조회
    @Override
    public List<Product> getTemporaryProductByUserId(Long userId) {

        return redisRepository.findAllByUserId(userId)
                .stream().map(ProductRedisHash::toDomain).toList();
    }


    // Redis에 프로덕트 option
    @Override
    public List<ProductOption> getTemporalOptionsByProductId(Long productId) {
        return optionRedisRepository.findByProductId(productId)
                .stream().map(ProductOptionRedisHash::toDomain).toList();
    }

    @Override
    public List<ProductImg> getProductImgsByProductId(Long productId) {
        return imgRedisRepository.findByProductId(productId)
                .stream().map(ProductImgRedisHash::toDomain).toList();
    }

    @Override
    public List<Tag> getTagsByProductId(Long productId) {
        return tagRedisRepository.findByProductId(productId)
                .stream().map(TagRedisHash::toDomain).toList();
    }

    // Redis에서 특정 사용자의 임시 저장된 상품을 삭제하는 메서드
    @Override
    public void deleteTemporaryProduct(String userId) {
        redisRepository.deleteById(Long.valueOf(userId));
    }


}
