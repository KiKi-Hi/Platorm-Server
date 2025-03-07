package org.jiyoung.kikihi.platform.application.in.product;

import java.util.Optional;

public interface ManageStockUseCase {
    // 재고 조회
    Optional<Integer> getProductStock(Long productId);

    // 재고 감소
    boolean decreaseProductStock(Long productId, Integer quantity);
}
