package org.example.oopPractice.gradeCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점 수 * 교과목 평점)의 합계 / 수강신청 총 학점 수
 * 일급 컬렉션 사용
 */
public class GradeCalculatorTest {
    // 학점계산기 도메인: 이수한 과목(객체지향프로그래밍, 자료구조, 중국어회화), 학점 계산기 (일단 생각나는 대로만)
    // 객체지향프로그래밍, 자료구조, 중국어회화 --> 이 객체들을 묶어서 과목(코스) 클래스

    /**
     * 핵심 포인트 - 협력을 설계
     */
    // 이수한 과목을 전달하여 평균학점 계산 요청 --> 학점 계산기 --> (학점 수 * 교과목 평점)의 합계 --> 과목(코스) 일급 컬렉션
    //                                                  --> 수강신청 총 학점 수           --> 과목(코스) 일급 컬렉션
    // 계산기가 직접 작업할 수도 있겠지만 요청하는 것으로 구상: 왜? 과목에는 해당 자료구조에 대한 정보(학점 수, 평점 등)를 갖고 있을 것이기 때문

    @Test
    @DisplayName("평균 학점을 계산한다.")
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
                new Course("자료구조", 3, "A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(courses); // 학점계산기 객체가 생성될 때 이수 과목들을 전달
        double gradeResult = gradeCalculator.calculateGrade(); // 이수한 과목을 전달 후, 성적을 계산해달라고 요청

        assertThat(gradeResult).isEqualTo(4.5);
    }
}
/*
* 객체지향 설계 및 구현 프로세스
* 1. 설계에 앞서 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민 (클래스에는 x)
*  - 모든 객체를 전부 생객해낼 순 없으니 일단 생각나는 대로만
* 2. 그 객체들 간의 관계를 고민 (클래스들 간의 x)
* 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
*  - 객체가 어떤 상태와 행동을 갖는지 결정되면
*  - 공통 상태, 행동을 갖는 객체를 타입으로 묶기
*  - 이를 기반으로 클래스 작성
* 4. 협력을 설계 (적절한 객체에 적절한 책임)
* 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
*  - 클라이언트와 협력할 수 있는 퍼블릭 인터페이스(퍼플릭 메서드) 정의
* 6. 앞의 퍼블릭 인터페이스를 구현하기
* */
