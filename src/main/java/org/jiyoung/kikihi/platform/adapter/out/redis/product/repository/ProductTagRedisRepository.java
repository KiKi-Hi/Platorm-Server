package org.jiyoung.kikihi.platform.adapter.out.redis.product.repository;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductImgRedisHash;
import org.jiyoung.kikihi.platform.adapter.out.redis.product.TagRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTagRedisRepository extends CrudRepository<TagRedisHash, Long> {

    List<TagRedisHash> findByProductId(Long productId);

}
