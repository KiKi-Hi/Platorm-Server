package org.jiyoung.kikihi.platform.application.in.keyboard.product;

import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

import java.util.*;

public interface TemporaryProductUseCase {

    /*
        판매할 상품을 임시 등록하는 유즈케이스
     */

    // 상품 임시저장 등록하기
    void saveTemporaryProduct(ProductRequest request);

    // 임시저장한 상품 목록 가져오기
    List<Product> getTemporaryProductsByUserId(Long userId);

    // 특정 임시저장 상품 가져오기
    Optional<Product> getTemporaryProductByUserId(Long userId);
}
