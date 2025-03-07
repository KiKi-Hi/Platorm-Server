package org.jiyoung.kikihi.platform.application.out.keyboard.product;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;

import java.util.List;

public interface TempProductPort {

    /// 저장
    //  상품 임시 저장하기
    void saveTemporaryProduct(ProductRedisHash product);

    // 옵션 임시 저장하기
    void saveTemporalOptions(ProductOption productOption);

    // 태그 임시 저장하기

    /// 조회
    // 사용자별 상품 임시저장 조회
    List<Product> getTemporaryProductByUserId(Long userId);

    // 아이디를 바탕으로 옵션 조회
    List<ProductOption> getTemporalOptionsByProductId(Long productId);


    /// 삭제
    // 임시저장된 상품 삭제하기
    void deleteTemporaryProduct(String userId);





}
