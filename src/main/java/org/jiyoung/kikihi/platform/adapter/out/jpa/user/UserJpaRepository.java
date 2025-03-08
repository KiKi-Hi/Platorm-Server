package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long>{
    Optional<UserJpaEntity> findByEmail(String email);
    UserJpaEntity findByName(String name);
}
