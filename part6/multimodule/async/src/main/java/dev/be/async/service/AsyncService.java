package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    // (1) 주입 받은 빈의 메서드를 호출
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName()); // 비동기로 동작하는지 확실히 알려면, 이 메서드를 처리하는 thread name을 찍어보면 된다.
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // (2) 인스턴스를 선언하고 생성해서, 생성된 인스턴스의 메서드를 호출 - 빈으로 등록되지 않은 인스턴스의 메서드를 호출하면 당연히 Spring 프레임워크의 프록시가 낄 자리가 없다.
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService0 = new EmailService();
        emailService0.sendMail();
        emailService0.sendMailWithCustomThreadPool();
    }

    // (3) 클래스 내부에 @Async 어노테이션을 붙인 메서드를 호출 - aop의 내부 호출 문제가 그대로 발생함
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async("defaultTaskExecutor")
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
