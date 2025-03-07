//package org.jiyoung.kikihi.platform.adapter.in.web.Request.response.product;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import org.jiyoung.kikihi.platform.adapter.in.web.Request.request.product.ProductOptionRequest;
//import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductBasicRequest;
//
//
//import java.util.Collections;
//import java.util.List;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class ProductFinalResponse {
//    @JsonProperty("textInfo")
//    private ProductBasicRequest textInfo;
//
//    @JsonProperty("imageInfo")
//    private List<ProductImgRequest> imageInfo;
//
//    @JsonProperty("optionInfo")
//    private List<ProductOptionRequest> optionInfo;
//
//    @JsonProperty("tagsInfo")
//    private List<ProductTagRequest> tagInfo;
//
//
//    // 정적 메서드 from을 추가하여 4개의 Request로부터 ProductResponseRequest 객체를 생성
//    public static ProductResponseRequest from(ProductTextRequest productTextRequest, List<ProductImgRequest> productImgRequests, List<ProductOptionRequest> productOptionRequests, List<ProductTagRequest> productTagRequests) {
//        return ProductResponseRequest.builder()
//                .textInfo(productTextRequest)
//                .imageInfo(productImgRequests)
//                .optionInfo(productOptionRequests)
//                .tagInfo(productTagRequests)
//                .build();
//    }
//    // 정적 메서드 from을 추가하여 4개의 Request로부터 ProductResponseRequest 객체를 생성 -> 단단히 잘못됐음
//    public static ProductResponseRequest fromEntity(Product product){
//        return ProductResponseRequest.builder()
//                .textInfo(ProductTextRequest.from(product))
//                .imageInfo(Collections.singletonList(ProductImgRequest.from((ProductImg) product.getProductImgs())))
//                .optionInfo(Collections.singletonList(ProductOptionRequest.from((Product) product.getProductOptions())))
//                .tagInfo(Collections.singletonList(ProductTagRequest.from((ProductTag) product.getProductTags())))
//                .build();
//    }
//
//}