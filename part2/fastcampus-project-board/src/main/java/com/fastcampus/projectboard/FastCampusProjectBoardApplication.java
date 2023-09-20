package com.fastcampus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FastCampusProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCampusProjectBoardApplication.class, args);
        // cf. https://youtrack.jetbrains.com/issue/IDEA-288922
        // IntelliJ 2021.3.2 ~ 2022.1.1 버전에서는 run에 오류 표시 뜨는 문제 있었음(AutoCloseable 관련)
        // 강의에서는 설정 > Editor > Inspections에서
        // Resource management > AutoCloseable used without 'try-with-resources' 관련 검사 문제
        // => Ignore 'AutoCloseable' returned by this method로 이 메서드에 대해서는 AutoCloseable 검사 중단
    }

}
