package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.adapter.out.jpa.BaseEntity;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Builder
@Table(name = "product")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductJpaEntity extends BaseEntity {

    // JPA용 ID 필드 (기본키)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Embedded
    private ProductInfoJpaEntity info;

    @Embedded
    private ProductStatisticsJpaEntity statistics;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOptionJpaEntity option;

    @org.springframework.data.annotation.Id
    private String elasticsearchId;

    // from | DTO를 진짜 JPA 엔티티로!!
    public static ProductJpaEntity from(Product product) {
        return ProductJpaEntity.builder()
                .info(ProductInfoJpaEntity.from(product.getInfo()))
                .statistics(ProductStatisticsJpaEntity.from(product.getStatistics()))
                .build();
    }

    // toDomain | JPA 엔티티를 DTO같은걸로!
    public Product toDomain(){
        return Product.builder()
                .id(id)
                .info(info.toDomain())
                .statistics(statistics.toDomain())
                .build();
    }
}
