package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductOptionRequest;

import java.util.*;

@Getter
@Builder
@AllArgsConstructor
public class ProductOption {

    // embedded (X) vs 연관관계 (0)
    private Long id;
    private Long productId;// manytoone의 joincolumn했을때의 들어가는 id와 유사
    private String color;
    private String switchType;
    private String layout;
    private boolean isWireless;
    private int extraPrice; // 옵션 추가 가격

    ///  개별 변수를 넣어서 만드는 생성자, Request를 활요한 생성자가 있기에 활용성은 낮아질 듯.
    public static ProductOption of(Long productId, String color, String switchType, String layout, boolean isWireless, int extraPrice) {
        return ProductOption.builder()
                .productId(productId)
                .color(color)
                .switchType(switchType)
                .layout(layout)
                .isWireless(isWireless)
                .extraPrice(extraPrice)
                .build();
    }

    /// Request를 활요한 생성자
    public static ProductOption of(ProductOptionRequest request){
        return ProductOption.builder()
                .productId(request.getProductId())
                .color(request.getColor())
                .switchType(request.getSwitchType())
                .layout(request.getLayout())
                .isWireless(request.isWireless())
                .extraPrice(request.getExtraPrice())
                .build();
    }

    ///  List 생성자 , 기존의 of를 활용하여 LIST 옵션을 생성한다.
    public static List<ProductOption> of(List<ProductOptionRequest> productOptions){

        return productOptions.stream()
                .map(ProductOption::of).toList();
    }

    /// 비즈니스 로직
    public void changeProductId(Long productId) {
        this.productId = productId;
    }


}
