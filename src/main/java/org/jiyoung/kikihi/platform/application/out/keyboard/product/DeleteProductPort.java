package org.jiyoung.kikihi.platform.application.out.keyboard.product;

public interface DeleteProductPort {

    // 상품 삭제하기
    void deleteProduct(Long productId);

    // 임시저장된 상품 삭제하기
    void deleteTemporaryProduct(String userId);

}
