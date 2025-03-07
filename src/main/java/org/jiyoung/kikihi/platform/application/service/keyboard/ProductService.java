package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.CreateProductUseCase;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.GetProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final SaveProductPort savePort;
    private final LoadProductPort loadPort;
    private final DeleteProductPort deletePort;

    // 의존성
    private final CategoryPort categoryPort;
    private final OptionPort optionPort;
//    private final TagPort tagPort;

    /// 상품 저장하는 기능
    // 상품 저장
    // 임시저장과 일반 저장 모두, 똑같이 저장되어야한다.
    @Override
    public Product create(ProductRequest request) {

        // 포트를 통해 상품도 저장함

        Product product = savePort.createProduct(Product.of(request));

        // 포토를 통해 옵션도 한번에 저장함
        List<ProductOption> options = ProductOption.of(request.getOptions());

        options.forEach(option -> {
            option.changeProductId(product.getId());
            optionPort.saveOption(option);
        });

        // 포트를 통해 태그도 저장함
//        tagPort.SaveTage()

        return null;
    }

    /// Load 관련
    // 상품 상세 조회
    @Override
    public Optional<Product> getProductById(Long productId) {
        return loadPort.loadProductById(productId);// interface의 함수를 실행시키니까 -> port건들일일이 없음
        //-> 구현체만 바꿔끼운다. port - adapter 구조!@
    }

    // 상품 목록 조회 (최신순)
    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return loadPort.loadProducts(pageable);
    }

    // 상품 목록 조회 (좋아요순)
    @Override
    public Page<Product> getProductsByLike(Pageable pageable) {
        return loadPort.loadProductsByLike(pageable);
    }

    // 상품 목록 조회 (조건순)
    @Override
    public Page<Product> getProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice) {
        return loadPort.loadProductsByCondition(pageable, productTitle, minPrice, maxPrice);
    }

}
