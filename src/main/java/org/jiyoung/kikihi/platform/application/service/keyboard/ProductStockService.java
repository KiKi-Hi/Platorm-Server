package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.ManageStockUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductStockService implements ManageStockUseCase {
    private final SaveProductPort saveProductPort;

    @Override
    public Optional<Integer> getProductStock(Long productId){return saveProductPort.findStockByProductId(productId); }

    @Override
    public boolean decreaseProductStock(Long productId, Integer quantity) {return saveProductPort.decreaseProduct(productId,quantity);

    }
}
