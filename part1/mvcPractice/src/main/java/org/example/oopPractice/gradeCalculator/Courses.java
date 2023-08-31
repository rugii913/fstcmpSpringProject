package org.example.oopPractice.gradeCalculator;

import java.util.List;

public class Courses {

    // 일급 컬렉션?? 컬렉션을 래핑하면서 다른 필드를 갖지 않는 것
    // 래핑된 컬렉션의 정보들만으로 계산할 수 있는 것들은 해당 일급 컬렉션이 갖고 있는 메서드로 해결한다.
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        return courses.stream().mapToDouble(Course::multiplyCreditAndCourseGrade).sum();
    }

    public double calculateTotalCompletedCredit() {
        return courses.stream().mapToInt(Course::getCredit).sum();
    }
}
