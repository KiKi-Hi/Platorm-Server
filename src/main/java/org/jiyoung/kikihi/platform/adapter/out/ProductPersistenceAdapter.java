package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductJpaEntity;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductJpaRepository;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort, DeleteProductPort {

    /*
        Redis와 JPA 복합 사용 Adapter
     */

    private final ProductJpaRepository repository;
    private final ProductRedisRepository redisRepository;

    /**
     * Redis에 임시 저장된 상품을 저장하는 메서드
     */
    @Override
    public void saveTemporaryProduct(ProductRedisHash product) {
        redisRepository.save(product);  // Redis에 상품 임시 저장
    }

    /**
     * 새로운 상품을 JPA 저장소에 저장하는 메서드
     */
    @Override
    public Product createProduct(Product product) {
        return repository.save(ProductJpaEntity.from(product))  // JPA 저장소에 상품 저장
                .toDomain();  // 저장 후 도메인 객체로 변환하여 반환
    }

    /**
     * Redis에서 특정 상품의 재고를 조회하는 메서드
     */
    @Override
    public Optional<Integer> findStockByProductId(Long productId) {
        return redisRepository.findStockById(productId);  // Redis에서 재고 조회
    }

    /**
     * Redis에서 상품 재고를 감소시키는 메서드
     */
    @Override
    public boolean decreaseProduct(Long productId, Integer quantity) {
        return redisRepository.decreaseStock(productId, quantity);  // Redis에서 상품 재고 감소
    }

    /**
     * JPA 저장소에서 상품을 ID로 조회하는 메서드
     */
    @Override
    public Optional<Product> loadProductById(Long productId) {
        return repository.findById(productId)  // JPA 저장소에서 상품 조회
                .map(ProductJpaEntity::toDomain);  // 조회된 엔티티를 도메인 객체로 변환하여 반환
    }

    /**
     * JPA 저장소에서 모든 상품을 페이지네이션으로 조회하는 메서드
     */
    @Override
    public Page<Product> loadProducts(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(pageable)  // 최신 상품순으로 조회
                .map(ProductJpaEntity::toDomain);  // 조회된 엔티티를 도메인 객체로 변환하여 반환
    }

    /**
     * 좋아요 수를 기준으로 상품을 페이지네이션으로 조회하는 메서드
     */
    @Override
    public Page<Product> loadProductsByLike(Pageable pageable) {
        return repository.findAllByOrderByStatistics_LikeCountDesc(pageable)  // 좋아요 수 순으로 조회
                .map(ProductJpaEntity::toDomain);  // 조회된 엔티티를 도메인 객체로 변환하여 반환
    }

    /**
     * 상품 제목, 최소 가격, 최대 가격을 기준으로 상품을 필터링하여 조회하는 메서드
     */
    @Override
    public Page<Product> loadProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice) {
        Specification<ProductJpaEntity> condition = of(productTitle, minPrice, maxPrice);  // 필터 조건 생성
        return repository.findAll(condition, pageable)  // 조건에 맞는 상품 조회
                .map(ProductJpaEntity::toDomain);  // 조회된 엔티티를 도메인 객체로 변환하여 반환
    }

    /**
     * Redis에서 특정 사용자의 임시 저장된 상품을 조회하는 메서드
     */
    @Override
    public Optional<ProductRedisHash> getTemporaryProductByUserId(Long userId) {
        return redisRepository.findByUserId(userId);
    }

    /**
     * 상품을 삭제하는 메서드
     */
    @Override
    public void deleteProduct(Long productId) {
        repository.deleteById(productId);  // JPA 저장소에서 상품 삭제
    }

    /**
     * Redis에서 특정 사용자의 임시 저장된 상품을 삭제하는 메서드
     */
    @Override
    public void deleteTemporaryProduct(String userId) {
        redisRepository.deleteById(Long.valueOf(userId));  // Redis에서 임시 저장된 상품 삭제
    }
    // 동적 쿼리 생성 (필터링 조건을 Specification으로 처리)
    private Specification<ProductJpaEntity> of(String productTitle, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productTitle != null && !productTitle.isEmpty()) {
                predicates.add((Predicate) criteriaBuilder.like(root.get("productTitle"), "%" + productTitle + "%"));
            }

            if (minPrice != null) {
                predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return (jakarta.persistence.criteria.Predicate) predicates;
        };
    }
}
