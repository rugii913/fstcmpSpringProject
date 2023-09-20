package com.fastcampus.projectboard.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        // SpringResourceTemplateResolver -> 타임리프 템플릿 뷰 리졸브 해줌
        // 여기에 decoupled logic 하나만 더 세팅해준 것
        // 기본적으로 application.yaml에서 설정 불가능해서... 직접 자바 코드로 잡아서 yaml로 설정 가능하도록 한 것
        // defaultTemplateResolver.setUseDecoupledLogic(true); // yaml 설정 쓰게 하지 않고 바로 이렇게 설정해줘도 상관 없음

        return defaultTemplateResolver;
    }


    @RequiredArgsConstructor
    @Getter
    @ConstructorBinding
    @ConfigurationProperties("spring.thymeleaf3") // 기본적으로 application.yaml에서 설정 불가능해서... yaml로 설정 가능하도록 configuration property 잡아준 것
    // configuration property를 개발자가 직접 만든 경우엔 반드시 스캔 해줘야함
    // => app 메인 클래스에 @ConfigurationPropertiesScan 붙여줌
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        private final boolean decoupledLogic;

        /* // 롬복 사용
        public Thymeleaf3Properties(boolean decoupledLogic) {
            this.decoupledLogic = decoupledLogic;
        }

        public boolean isDecoupledLogic() {
            return this.decoupledLogic;
        }
         */
    }
}
