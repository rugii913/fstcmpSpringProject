package org.example.oopPractice.gradeCalculator;

import java.util.List;

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    /**
     * 핵심 포인트 - 협력을 설계
     */
    // 이수한 과목을 전달하여 평균학점 계산 요청 --> 학점 계산기 --> (학점 수 * 교과목 평점)의 합계 --> 과목(코스) 일급 컬렉션
    //                                                  --> 수강신청 총 학점 수           --> 과목(코스) 일급 컬렉션
    // 계산기가 직접 작업할 수도 있겠지만 요청하는 것으로 구상: 왜? 과목에는 해당 자료구조에 대한 정보(학점 수, 평점 등)를 갖고 있을 것이기 때문
    public double calculateGrade() {
        // (학점 수 * 교과목 평점)의 합계
        double totalMultipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        // 수강신청 총 학점 수
        double totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return totalMultipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
