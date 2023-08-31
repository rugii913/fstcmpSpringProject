package org.example.oopPractice.gradeCalculator;

import java.util.List;

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculateGrade() {
        // (학점 수 * 교과목 평점)의 합계
        courses.multiplyCreditAndCourseGrade();

        // 수강신청 총 학점 수
        int totalCompletedCredit = courses.stream().mapToInt(Course::getCredit).sum();

        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
