package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.*;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.TemporaryProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.*;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.ProductTag;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;
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
        System.out.println("전ProductTempService.saveTemporaryProduct" + request);
        ProductRedisHash tempProduct = ProductRedisHash.of(Product.of(request));
        System.out.println("후saveTemporaryProduct" + tempProduct);

        // Redis에 상품 저장 (상품 ID 생성됨)
        tempPort.saveTemporaryProduct(tempProduct);

        // 상품 저장 후, 생성된 productId를 기반으로 옵션 저장
        if (request.getOptions() != null) {
            request.getOptions().forEach(option -> {
                ProductOption domain = ProductOptionRedisHash.from(ProductOption.of(option)).toDomain();
                domain.changeProductId(tempProduct.getId()); // 상품 ID와 옵션 연결
                tempPort.saveTemporalOptions(domain);
            });
        }

        // 상품 저장 후, 생성된 productId를 기반으로 이미지 저장
        if (request.getImgs() != null) {
            request.getImgs().forEach(img -> {
                ProductImg domain = ProductImgRedisHash.of(ProductImg.of(img)).toDomain();
                domain.changeProductId(tempProduct.getId()); // 상품 ID와 이미지 연결
                tempPort.saveTemporalImg(domain);
            });
        }

        // 상품 저장 후, 생성된 productId를 기반으로 태그 저장
        if (request.getTags() != null) {
            request.getTags().forEach(tag -> {
                // Tag 도메인 생성 후 Redis 저장
                Tag tagDomain = TagRedisHash.from(Tag.of(tag)).toDomain();
                tagDomain.changeProductId(tempProduct.getId());
                tempPort.saveTemporalTag(tagDomain);

                // ProductTag 도메인 연결
                ProductTag.of(tempProduct.getId(), tagDomain.getId());
            });
        }
    }


    @Override
    public List<Product> getTemporaryProductsByUserId(Long userId) {
        List<Product> products = tempPort.getTemporaryProductByUserId(userId);

        products.forEach(product -> {
            product.changeOptions(tempPort.getTemporalOptionsByProductId(product.getId()));
            product.changeTags(tempPort.getTagsByProductId(product.getId()));
            product.changeImages(tempPort.getProductImgsByProductId(product.getId()));
        });

        return products;
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
