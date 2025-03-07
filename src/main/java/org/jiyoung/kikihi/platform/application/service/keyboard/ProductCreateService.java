//package org.jiyoung.kikihi.platform.application.service.keyboard;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
//import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
//import org.jiyoung.kikihi.platform.application.in.product.CreateProductUseCase;
//import org.jiyoung.kikihi.platform.application.in.product.CreateTemporaryProductUseCase;
//import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
//import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
//import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
//import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
//import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
//import org.jiyoung.kikihi.platform.domain.keyboard.product.*;
//import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisRepository.redisTemplate;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class ProductCreateService implements CreateProductUseCase, CreateTemporaryProductUseCase {
//
//    private final SaveProductPort savePort;
//    private final LoadProductPort loadPort;
//    private final DeleteProductPort deletePort;
//    // 의존성
//    private final CategoryPort categoryPort;
//    private final OptionPort optionPort;
//
//    /**
//     * 1. 임시 저장된 상품을 JPA 저장소에 저장하는 기능
//     */
//    @Override
//    public Product saveProductFromRedis(String userId) {
//        Optional<ProductRedisHash> tempProductOpt = loadPort.getTemporaryProduct(userId);
//
//        if (tempProductOpt.isEmpty()) {
//            throw new EntityNotFoundException("임시 저장된 상품이 없습니다.");
//        }
//
//        ProductRedisHash tempProduct = tempProductOpt.get();
//
//        // 카테고리 검증
//        if (!categoryPort.existsCategory(tempProduct.getCategoryCode())) {
//            throw new EntityNotFoundException("해당하는 카테고리가 존재하지 않습니다.");
//        }
//
//        // 상품 저장
////        Product entity = Product.builder()
////                .productName(tempProduct.getProductName())
////                .description(tempProduct.getDescription())
////                .categoryCode(tempProduct.getCategoryCode())
////                .snippet(new ProductSnippet(tempProduct.getSnippet().getProductTitle()
////                        ,tempProduct.getSnippet().getBrand()
////                        ,tempProduct.getSnippet().getManufacturer()))
////                .statistics(new ProductStatistics(tempProduct.getStatistics().getLikeCount(),tempProduct.getStatistics().getCommentCount(),tempProduct.getStatistics().getViewCount()))
////                .tags(new Tag(tempProduct.getTags().getProductId(), tempProduct.getTags().getTagId(),tempProduct.getTags().getProductId()))
////                .
////                .build();
//
//        // 상품 정보 Redis에 저장
//        String productKey = "product:" + entity.getId();
//        redisTemplate.opsForHash().put(productKey, "productTitle", entity.getProductTitle());
//        redisTemplate.opsForHash().put(productKey, "price", String.valueOf(entity.getPrice()));
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
//    }
//
//    /**
//     * 2. 상품 임시 저장 기능 (Redis에 저장)
//     */
//    @Override
//    public void saveTemporaryProduct(ProductRequest request) {
//        ProductRedisHash tempProduct = ProductRedisHash.builder()
//                .userId(request.getUserId())
//                .productTitle(request.getBasic().getTitle())
//                .brand(request.getBasic().getBrand())
//                .manufacturer(request.getBasic().getManufacturer())
//                .categoryCode(request.getBasic().getCategoryCode())
//                .options(request.getOptions())
//                .thumbnailImg(request.getImage().getThumbnailImage())
//                .mainImgs(request.getImage().getMainImages())
//                .descriptionImgs(request.getImage().getDescriptionImages())
//                .descriptionHtml(request.getImage().getDescriptionHtml())
//                .build();
//
//        savePort.saveTemporaryProduct(tempProduct);
//    }
//
//
//    /**
//     * 3. 임시 저장된 상품 조회 기능
//     */
//    @Override
//    public ProductRedisHash getTemporaryProduct(Long userId) {
//        return loadPort.getTemporaryProduct(String.valueOf(userId))
//                .orElseThrow(() -> new EntityNotFoundException("임시 저장된 상품이 없습니다."));
//    }
//
//}
