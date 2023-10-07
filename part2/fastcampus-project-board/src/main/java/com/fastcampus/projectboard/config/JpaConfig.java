package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.dto.securiy.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()) // security context 불러오기
                .map(SecurityContext::getAuthentication) // security context에 있는 인증 정보 가져오기
                .filter(Authentication::isAuthenticated) // 인증되었는지(로그인 한 상태인지) 확인
                .map(Authentication::getPrincipal) // 로그인 정보 꺼내오기
                .map(BoardPrincipal.class::cast) // 타입 캐스팅
                .map(BoardPrincipal::getUsername); // 실제 사용자 정보 불러오기
    }
}
