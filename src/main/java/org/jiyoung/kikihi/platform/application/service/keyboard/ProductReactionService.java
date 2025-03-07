package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.application.in.keyboard.product.ReactionProductUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductReactionService implements ReactionProductUseCase {


    @Override
    public void addLike(Long productId, Long userId) {

    }

    @Override
    public void RemoveLike(Long productId, Long userId) {

    }
}
