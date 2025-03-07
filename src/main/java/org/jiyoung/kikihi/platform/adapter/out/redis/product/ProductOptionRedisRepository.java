package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRedisRepository extends CrudRepository<ProductOptionRedisHash, Long> {

}
