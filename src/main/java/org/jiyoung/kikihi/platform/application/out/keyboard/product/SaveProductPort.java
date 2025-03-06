package org.jiyoung.kikihi.platform.application.out.keyboard.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

public interface SaveProductPort {

    // 상품 등록하기
    Product createProduct(Product product);

    // 상품 재고 -1하기 -> 서비스에서 엮은 것이 좋음 (사용자 요청과는 관련없음-결제되면 깎자)
    void minusProduct(Long productId);
}
