package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.category;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id") // 실제 DB 컬럼 이름이 category_id라면...
    private Long categoryId;

    private Long parentId;

    @Column(name = "name", nullable = false)
    private String name;

    private String code;

    private String description;


    // from
    public static CategoryJpaEntity from(Category category) {
        return CategoryJpaEntity.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .parentId(category.getParentId() != null ? category.getParentId() : null)
                .code(category.getCode())
                .description(category.getDescription())
                .build();
    }

    // toDomain (CategoryJpaEntity -> Category 변환)
    public Category toDomain() {
        return Category.builder()
                .id(categoryId)
                .name(name)
                .parentId(parentId != null ? parentId : null)
                .code(code)
                .description(description)
                .build();
    }
}
