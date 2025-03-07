package org.jiyoung.kikihi.platform.application.in.product;

import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

public interface CreateProductUseCase {

    /*
        판매할 상품을 등록하는 유즈케이스
     */

    // 임시저장된 상품 jpa에 저장하기
    Product saveProductFromRedis(String productId);
}
