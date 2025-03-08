package org.jiyoung.kikihi.platform.adapter.out.redis.product.repository;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductImgRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductStatisticsRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStatisticsRedisRepository extends CrudRepository<ProductStatisticsRedisHash, Long> {

    List<ProductStatisticsRedisHash> findByProductId(Long productId);

}
