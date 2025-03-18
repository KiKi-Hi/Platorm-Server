package org.jiyoung.kikihi.customizer.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "products") // MongoDB 컬렉션 이름
public class Products {

    @Id
    private Long id;

    @Field("created_at")
    private String createdAt;

    @Field("updated_at")
    private String updatedAt;

    @Field("검색어")
    private String searchKeyword;

    @Field("정렬기준")
    private String sortCriteria;

    @Field("상품명")
    private String productName;

    @Field("판매처")
    private String seller;

    @Field("상품 썸네일")
    private String productThumbnail;

    @Field("상품 url")
    private String productUrl;

    @Field("정가")
    private String originalPrice;

    @Field("할인율")
    private String discountRate;

    @Field("할인가")
    private String discountedPrice;

    @Field("배송비")
    private String shippingFee;

    @Field("별점")
    private String rating;

    @Field("리뷰수")
    private String reviewCount;

    @Field("재고 수량")
    private String stockQuantity;

    @Field("해외 배송")
    private boolean internationalShipping;

    @Field("슈퍼 적립")
    private boolean superReward;

    @Field("상품정보")
    private String productInfo;

    @Field("관련 태그")
    private String relatedTags;

    @Field("상품 상세 텍스트")
    private String productDetailText;

    @Field("상품 상세 이미지")
    private List<String> productDetailImages;

    @Field("상품정보 제공공시")
    private String productInfoDisclosure;

    @Field("거래조건에 관한 정보")
    private String transactionConditions;

    // Getter and Setter methods (생략)
}
