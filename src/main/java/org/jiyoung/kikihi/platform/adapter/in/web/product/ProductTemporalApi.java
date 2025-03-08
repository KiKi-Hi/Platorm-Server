package org.jiyoung.kikihi.platform.adapter.in.web.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.common.response.ApiResponse;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.TempProductGetRequest;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product.ProductResponse;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.TemporaryProductUseCase;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductTemporalApi {


    private final TemporaryProductUseCase temporaryService;

    // 임시 저장 요청
    @PostMapping("/temporary/save")
    public ApiResponse<String> saveTemporaryProduct(@RequestBody ProductRequest request) {
        temporaryService.saveTemporaryProduct(request);
        return ApiResponse.created("상품이 임시 저장되었습니다.");
    }

    // 사용자별 임시 목록 조회
    @GetMapping("/temporary/{userId}")
    public ApiResponse<List<ProductResponse>> getTemporaryProduct(@PathVariable("userId") Long userId) {
        List<Product> products = temporaryService.getTemporaryProductsByUserId(userId);
        System.out.println(products);

        List<ProductResponse> productResponse = products.stream()
                .map(ProductResponse::from)
                .toList();
        return ApiResponse.ok(productResponse);
    }

    // 임시 저장 값 가져오기
    @GetMapping("/temporary")
    public ApiResponse<ProductResponse> getTemporaryProducts(@RequestBody TempProductGetRequest request) {
        Product product = temporaryService.getTemporaryProductByIndexAndUserId(request.getUserId(), request.getIndex())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 임시저장 객체가 존재하지 않습니다."));

        return ApiResponse.ok(ProductResponse.from(product));
    }
}

