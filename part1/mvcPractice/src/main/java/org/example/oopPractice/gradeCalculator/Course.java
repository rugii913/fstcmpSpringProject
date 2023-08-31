package org.example.oopPractice.gradeCalculator;

public class Course {

    private final String subject; // 과목명
    private final int credit; // 학점
    private final String grade; // 성적 (A+, A, B+, ...)

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public double getGradeToNumber() { // 이 부분이 중점이 아니라서 대충 구현
        double grade = 0;
        switch (this.grade) {
            case "A+":
                grade = 4.5;
                break;
            case "A":
                grade = 4.0;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "B":
                grade = 3.0;
                break;
            case "C+":
                grade = 2.5;
                break;
            case "C":
                grade = 2.0;
                break;
        }
        return grade;
    }
}
