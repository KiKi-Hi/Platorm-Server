package org.jiyoung.kikihi.platform.application.service.user;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.user.JoinRequest;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.UserJpaEntity;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.UserJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 가입하고 도대체 뭘 반환해야하는 거임..???
    public void joinProcess(JoinRequest dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String name=dto.getName();

        System.out.println(email+","+password+","+name);
        boolean isExist = userRepository.findByEmail(email).isPresent();

        if (isExist) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        UserJpaEntity userJpaEntity = userRepository.save(UserJpaEntity.from(email, bCryptPasswordEncoder.encode(password), name));
        System.out.println(userJpaEntity.getEmail()+":"+ userJpaEntity.getPassword()+":"+ userJpaEntity.getRole());
    }

}