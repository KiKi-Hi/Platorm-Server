package org.jiyoung.kikihi.platform.adapter.out.redis.product.repository;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.ProductRedisHash;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface ProductRedisRepository extends CrudRepository<ProductRedisHash, Long>{

    // 재고 -1
    final RedisTemplate<String,Integer> redisTemplate = new RedisTemplate<>();
    static final String STOCK_KEY_PREFIX="product:stock";

    private String getStockKey(Long productId) {
        return STOCK_KEY_PREFIX + productId;
    }

    // 재고 조회
    public default Optional<Integer> findStockById(Long productId){
        Integer stock=redisTemplate.opsForValue().get(getStockKey(productId));
        return Optional.ofNullable(stock);
    }
    // 재고 감소
    public default boolean decreaseStock(Long productId, Integer quantity) {
        String key = getStockKey(productId);

        // Lua 스크립트 활용 (원자적 감소)
        String script = """
            local stock = redis.call('GET', KEYS[1])
            if stock and tonumber(stock) >= tonumber(ARGV[1]) then
                return redis.call('DECRBY', KEYS[1], ARGV[1])
            else
                return -1
            end
        """;
        Long result = redisTemplate.execute((RedisCallback<Long>) (connection) -> connection.scriptingCommands()
                .eval(script.getBytes(), ReturnType.INTEGER, 1, key.getBytes(), String.valueOf(quantity).getBytes()));

        return result != null && result >= 0;
    }

    /**
     * 상품 재고 설정 (초기화)
     */
    public default void setStock(Long productId, Integer stock) {
        redisTemplate.opsForValue().set(getStockKey(productId), stock);
    }


}
