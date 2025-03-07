package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.TemporaryProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTempService implements TemporaryProductUseCase {

    private final SaveProductPort savePort;
    private final LoadProductPort loadPort;
    private final DeleteProductPort deletePort;

    // 의존성
    private final CategoryPort categoryPort;
    private final OptionPort optionPort;
//    private final TagPort tagPort;


    /// 상품 임시 저장
    @Override
    public void saveTemporaryProduct(ProductRequest request) {

        // Request를 바탕으로 객체저장
        // 순수 도메인이기에 null 값도 허용한다.
        Product product = Product.of(request);

        // 순수 도메인 객체를 바탕으로 Redis생성
        ProductRedisHash tempProduct = ProductRedisHash.of(product);

        // Redis 값 저장
        savePort.saveTemporaryProduct(tempProduct);
    }


    /// userId를 바탕으로 상품 임시 저장
    @Override
    public ProductRedisHash getTemporaryProduct(Long userId) {

        return loadPort.getTemporaryProductByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("임시 저장된 상품이 없습니다."));
    }

}
