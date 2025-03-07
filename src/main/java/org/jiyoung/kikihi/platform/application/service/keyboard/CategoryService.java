package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.CategoryRequest;
import org.jiyoung.kikihi.platform.application.in.keyboard.category.CreateCategoryUseCase;
import org.jiyoung.kikihi.platform.application.in.keyboard.category.GetCategoryUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements CreateCategoryUseCase, GetCategoryUseCase {

    private final CategoryPort categoryPort;

    @Override
    public Category createCategory(CategoryRequest request) {
        Category parent = null;

        if (categoryPort.existsCategory(request.getCode())) {
            throw new IllegalStateException("해당코드가 이미 존재합니다.");
        }

        Category category = Category.of(request.getParentId(), request.getName(), request.getCode(), request.getDescription());

        return categoryPort.createCategory(category);
    }


    @Override
    public Optional<Category> findCategoryByCode(String code) {
        return categoryPort.loadCategoryByCode(code);
    }


}
