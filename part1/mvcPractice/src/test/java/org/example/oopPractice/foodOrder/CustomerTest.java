package org.example.oopPractice.foodOrder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CustomerTest {
    
    @Test
    @DisplayName("메뉴 이름에 해당하는 요리를 주문한다.")
    void orderTest() {
        Customer customer = new Customer();
        Menu menu = new Menu(List.of(new MenuItem("돈까스", 5000), new MenuItem("냉면", 7000)));
        Chef chef = new Chef();

        assertThatCode(() -> customer.order("돈까스", menu, chef)).doesNotThrowAnyException();
        // 주문할 때 menu와 chef도 같이 전달
    }
}
/*
 * 음식점에서 음식 주문하는 과정 구현
 * 객체지향 설계 및 구현 프로세스
 * 1. 설계에 앞서 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민 (클래스에는 x)
 *  - 모든 객체를 전부 생객해낼 순 없으니 일단 생각나는 대로만
 *  => 손님(o), 메뉴판(o), 돈까스/냉면/만두 등 메뉴 항목(o), 요리사(o), 요리(o)
 * 2. 그 객체들 간의 관계를 고민 (클래스들 간의 x)
 *  => 손님 -- 메뉴판
 *  => 손님 -- 요리사
 *  => 요리사 -- 요리
 * 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
 *  - 객체가 어떤 상태와 행동을 갖는지 결정되면
 *  - 공통 상태, 행동을 갖는 객체를 타입으로 묶기
 *  - 이를 기반으로 클래스 작성
 *  => 손님 -- 손님 타입
 *  => 돈까스/냉면/만두 -- 요리 타입(간단하게 만들 수 있을 것 같다.)
 *  => 메뉴판 -- 메뉴판 타입
 *  => 메뉴 -- 메뉴 타입(간단하게 만들 수 있을 것 같다.)
 * 4. 협력을 설계 (적절한 객체에 적절한 책임)
 * 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
 *  - 클라이언트와 협력할 수 있는 퍼블릭 인터페이스(퍼플릭 메서드) 정의
 * 6. 앞의 퍼블릭 인터페이스를 구현하기
 * */