package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
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
    @Override
    public Product create(ProductRequest request) {

        // 포트를 통해 상품도 저장함
//        savePort.createProduct()

        // 포토를 통해 옵션도 저장함
//        optionPort.saveOption()

        // 포트를 통해 태그도 저장함
//        tagPort.SaveTage()

        return null;
    }

    /**
     * 1. 임시 저장된 상품을 JPA 저장소에 저장하는 기능
     */
    @Override
    public Product createProductByTemp(Long userId, Long index) {

        /// 중요한 포인트
        // 임시저장을 불러오고 나서, 상품 생성에 필요한 객체가 다 존재하는지 체크해야한다.
        // 레디스에서 불러온다.
        // 포트를 활용해서 상품 저장, 옵션 저장, 태그 저장 -> 기존의 함수를 재사용 하는 것이 좋음.


        Optional<ProductRedisHash> tempProductOpt = loadPort.getTemporaryProductByUserId(userId);

        if (tempProductOpt.isEmpty()) {
            throw new EntityNotFoundException("임시 저장된 상품이 없습니다.");
        }

        ProductRedisHash tempProduct = tempProductOpt.get();
        Product domain = tempProduct.toDomain();

        // 카테고리 검증
        if (!categoryPort.existsCategory(tempProduct.getInfo().getCategoryCode())) {
            throw new EntityNotFoundException("해당하는 카테고리가 존재하지 않습니다.");
        }

        // 옵션 저장

        // 태그 저장

//        // 상품 정보 Redis에 저장
//        String productKey = "product:" + entity.getId();
//        redisTemplate.opsForHash().put(productKey, "productTitle", entity.getInfo().getProductTitle());
//        redisTemplate.opsForHash().put(productKey, "price", String.valueOf(entity.getInfo().getProductPrice()));
//
//        // 태그 처리
//        List<Tag> tags = tempProduct.getTags().stream()
//                .map(tagName -> Tag.builder()
//                        .name(tagName)
//                        .build())
//                .toList();
//
//        // Redis에 태그 저장
//        tags.forEach(tag -> {
//            // 상품과 태그 간 관계를 Redis에 저장
//            redisTemplate.opsForSet().add("product:" + entity.getId() + ":tags", tag.getId());
//            redisTemplate.opsForSet().add("tag:" + tag.getId() + ":products", entity.getId());
//        });
//
//        // 옵션 저장
//        tempProduct.getOptions().forEach(option -> {
//            ProductOption productOption = ProductOption.of(
//                    entity.getId(),  // 저장된 상품의 ID
//                    option.getColor(),
//                    option.getSwitchType(),
//                    option.getLayout(),
//                    option.isWireless(),
//                    option.getExtraPrice()
//            );
//            optionPort.saveOption(productOption);  // 옵션 저장
//        });
//
//        // Redis에서 임시 저장된 상품 삭제
//        deletePort.deleteTemporaryProduct(userId);
//
//        return entity;
        return null;
    }

    /// Load 관련
    @Override
    public Optional<Product> getProductById(Long productId) {
        return loadPort.loadProductById(productId);// interface의 함수를 실행시키니까 -> port건들일일이 없음
        //-> 구현체만 바꿔끼운다. port - adapter 구조!@
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return loadPort.loadProducts(pageable);
    }

    @Override
    public Page<Product> getProductsByLike(Pageable pageable) {
        return loadPort.loadProductsByLike(pageable);
    }

    @Override
    public Page<Product> getProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice) {
        return loadPort.loadProductsByCondition(pageable, productTitle, minPrice, maxPrice);
    }

}
