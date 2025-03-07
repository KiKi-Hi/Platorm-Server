package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisRepository;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.TempProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
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

    /// 저장 기능
    // Redis에 상품 임시 저장하기
    @Override
    public void saveTemporaryProduct(ProductRedisHash product) {
        redisRepository.save(product);  // Redis에 상품 임시 저장
    }

    // 레디스에 옵션 임시 저장하기
    @Override
    public void saveTemporalOptions(ProductOption options) {

        optionRedisRepository.save(ProductOptionRedisHash.from(options));

    }

    // 레디스에 태그 임시 저장하기


    ///  조회 기능

    // Redis에서 특정 사용자의 임시 저장된 상품을 조회하는 메서드
    @Override
    public List<ProductRedisHash> getTemporaryProductByUserId(Long userId) {
        return redisRepository.findAllByUserId(userId);
    }


    /// 삭제 기능

    // Redis에서 특정 사용자의 임시 저장된 상품을 삭제하는 메서드
    @Override
    public void deleteTemporaryProduct(String userId) {
        redisRepository.deleteById(Long.valueOf(userId));  // Redis에서 임시 저장된 상품 삭제
    }


}
