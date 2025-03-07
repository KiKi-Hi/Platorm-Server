package org.jiyoung.kikihi.platform.adapter.in.web.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.common.response.ApiResponse;
import org.jiyoung.kikihi.common.response.pageable.PageRequest;
import org.jiyoung.kikihi.common.response.pageable.PageResponse;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product.ProductDetailResponse;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product.ProductListResponse;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.CreateProductUseCase;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.GetProductOptionUseCase;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.GetProductUseCase;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.ManageStockUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApi {

    private final CreateProductUseCase createService;
    private final DeleteProductPort deleteService;
    private final GetProductUseCase getService;
    private final GetProductOptionUseCase optionService;
    private final ManageStockUseCase manageStockService;

    /**
     * Redis에서 임시 저장된 제품을 실제 DB에 저장
     */
    @PostMapping("/save")
    public ApiResponse<String> submitTemporaryProduct(@RequestBody TempProductSaveRequest request ) {
        Product savedProduct = createService.createProductByTemp(request.getUserId(), request.getIndex());
        return ApiResponse.ok("Product saved successfully");
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> getProductById(@PathVariable("productId") Long productId) {

        Product product = getService.getProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException("해당 상품은 존재하지 않습니다."));

        List<ProductOption> options = optionService.getProductOptions(productId);

        return ApiResponse.ok(ProductDetailResponse.from(product, options));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ProductListResponse>> list(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<Product> result = getService.getProducts(pageable);
        List<ProductListResponse> dtolist = ProductListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));
    }

    // 상품 목록 제공 (페이징, 정렬, 필터링)
    @GetMapping
    public ApiResponse<PageResponse<ProductListResponse>> getAllProducts(
            PageRequest pageRequest,
            @RequestParam(value = "productTitle", required = false) String productTitle,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<Product> result = getService.getProductsByCondition(pageable, productTitle, minPrice, maxPrice);
        List<ProductListResponse> dtolist = ProductListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@PathVariable("productId") Long productId) {
        deleteService.deleteProduct(productId);

        return ApiResponse.ok("성공적으로 삭제되었습니다.");
    }

    // 재고 조회
    @GetMapping("/stock/{productId}")
    public ApiResponse<Optional<Integer>> getProductDetail(@PathVariable("productId") Long productId) {
        Optional<Integer> productStock = manageStockService.getProductStock(productId);
        return ApiResponse.ok(productStock);
    }

}
