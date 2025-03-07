package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.TemporaryProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTempService implements TemporaryProductUseCase {

    private final TempProductPort tempPort;

    // 의존성
    private final CategoryPort categoryPort;


    /// 상품 임시 저장
    @Override
    @Transactional
    public void saveTemporaryProduct(ProductRequest request) {
        // 상품 정보를 바탕으로 Product 객체를 생성
        ProductRedisHash tempProduct = ProductRedisHash.of(Product.of(request));

        // Redis에 상품 저장 (상품 ID 생성됨)
        tempPort.saveTemporaryProduct(tempProduct);

        // 상품 저장 후, 생성된 productId를 기반으로 옵션 저장
        request.getOptions().forEach(option -> {
            ProductOption domain = ProductOptionRedisHash.from(ProductOption.of(option)).toDomain();

            domain.changeProductId(tempProduct.getId()); // 상품 ID와 옵션 연결

            tempPort.saveTemporalOptions(domain);
        });
    }


    /// userId를 바탕으로 상품 가져오기
    @Override
    public List<Product> getTemporaryProductsByUserId(Long userId) {
        List<Product> list = tempPort.getTemporaryProductByUserId(userId);

        list.forEach(product -> {
            List<ProductOption> options = tempPort.getTemporalOptionsByProductId(product.getId());
            product.changeOptions(options);
        });

        return list;
    }

    @Override
    public Optional<Product> getTemporaryProductByIndexAndUserId(Long userId, int index) {

        // 상품 정보 가져오기
        List<Product> list = tempPort.getTemporaryProductByUserId(userId);
        Product product = list.get(index - 1);

        // 옵션과 가져오기
        List<ProductOption> options = tempPort.getTemporalOptionsByProductId(product.getId());

        // 옵션 연결하기
        product.changeOptions(options);

        return Optional.of(product);
    }

}
