//package org.jiyoung.kikihi.platform.adapter.out.redis.product;
//
//
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;
//
//import java.io.Serializable;
//
//@Getter
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@RedisHash(value = "productSnippet", timeToLive = 3600) // 1시간 TTL
//public class ProductSnippetRedisHash implements Serializable {
//
//    @Id
//    private String productTitle;
//
//    private String brand;
//    private String manufacturer;
//
//    /// 생성자
//    public static ProductSnippet of(String productTitle, String brand, String manufacturer) {
//        return ProductSnippet.builder()
//                .productTitle(productTitle)
//                .brand(brand)
//                .manufacturer(manufacturer)
//                .build();
//    }
//}
