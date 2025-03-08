package org.jiyoung.kikihi.platform.adapter.out.redis.product.repository;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductImgRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductOptionRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgRedisRepository extends CrudRepository<ProductImgRedisHash, Long> {

    List<ProductImgRedisHash> findByProductId(Long productId);

}
