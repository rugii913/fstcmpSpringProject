package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter // 기존 WebSecurityConfigurerAdapter는 deprecated
// 상속 -> 빈 컴포넌트 방식으로 변경 // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter 참고
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin().and()
                .build();
    }
}
