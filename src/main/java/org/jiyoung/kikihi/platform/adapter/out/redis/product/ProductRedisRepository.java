package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<ProductRedisHash, Long> {
    //재고 -1

}
