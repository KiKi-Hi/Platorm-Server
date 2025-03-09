package org.jiyoung.kikihi.platform.adapter.out.redis.product.repository;

import org.jiyoung.kikihi.platform.adapter.out.redis.product.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CustomRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Map<String, ProductRedisHash> findAllByUserId(Long userId) {
        // 1. userId에 대한 product 키 조회
        Set<String> productKeys = redisTemplate.keys("product:" + userId + ":*");

        if (productKeys == null || productKeys.isEmpty()) {
            return Collections.emptyMap();
        }

        // 2. product 데이터를 가져오기
        List<Object> products = redisTemplate.opsForValue().multiGet(productKeys);
        if (products == null || products.isEmpty()) {
            return Collections.emptyMap();
        }

        // 3. productId 리스트 추출
        List<String> productIds = productKeys.stream()
                .map(key -> key.split(":")[2]) // "product:userId:{productId}" 에서 productId만 추출
                .collect(Collectors.toList());

        // 4. productId에 해당하는 연관 데이터 키 조회
        Set<String> optionKeys = productIds.stream()
                .map(id -> "ProductOption:productId:" + id + ":*")
                .flatMap(pattern -> redisTemplate.keys(pattern).stream())
                .collect(Collectors.toSet());

        Set<String> tagKeys = productIds.stream()
                .map(id -> "Tag:productId:" + id + ":*")
                .flatMap(pattern -> redisTemplate.keys(pattern).stream())
                .collect(Collectors.toSet());

        Set<String> imgKeys = productIds.stream()
                .map(id -> "productImg:productId:" + id + ":*")
                .flatMap(pattern -> redisTemplate.keys(pattern).stream())
                .collect(Collectors.toSet());

        // 5. Redis에서 multiGet으로 가져오기 (null 체크 추가)
        List<Object> options = Optional.ofNullable(redisTemplate.opsForValue().multiGet(optionKeys)).orElse(new ArrayList<>());
        List<Object> tags = Optional.ofNullable(redisTemplate.opsForValue().multiGet(tagKeys)).orElse(new ArrayList<>());
        List<Object> imgs = Optional.ofNullable(redisTemplate.opsForValue().multiGet(imgKeys)).orElse(new ArrayList<>());

        // 6. 결과를 Map 형태로 묶어서 반환
        Map<String, ProductRedisHash> result = new HashMap<>();

        for (int i = 0; i < productIds.size(); i++) {
            String productId = productIds.get(i);
            ProductRedisHash product = (ProductRedisHash) products.get(i);

            // 해당 productId에 대한 데이터만 필터링
            List<ProductOptionRedisHash> optionList = options.stream()
                    .filter(obj -> obj instanceof ProductOptionRedisHash)
                    .map(obj -> (ProductOptionRedisHash) obj)
                    .filter(option -> option.getProductId().equals(productId))
                    .collect(Collectors.toList());

            List<TagRedisHash> tagList = tags.stream()
                    .filter(obj -> obj instanceof TagRedisHash)
                    .map(obj -> (TagRedisHash) obj)
                    .filter(tag -> tag.getProductId().equals(productId))
                    .collect(Collectors.toList());

            List<ProductImgRedisHash> imgList = imgs.stream()
                    .filter(obj -> obj instanceof ProductImgRedisHash)
                    .map(obj -> (ProductImgRedisHash) obj)
                    .filter(img -> img.getProductId().equals(productId))
                    .collect(Collectors.toList());

            // 연관 데이터 설정
            product.setOptions(optionList);
            product.setTags(tagList);
            product.setImgs(imgList);

            result.put(productId, product);
        }

        return result;
    }
}
