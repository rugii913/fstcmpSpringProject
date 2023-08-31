package org.example.oopPractice.calculator;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 + operand2;
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 - operand2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 * operand2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 / operand2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int operand1, final int operand2);

    public static int calculate(int operand1, String operator, int operand2) {
        ArithmeticOperator arithmeticOperator = Arrays.stream(values()) // values(): 모든 enum 객체들을 가져옴
                .filter(v -> v.operator.equals(operator)) // array로 가져온 객체들의 operator가 파라미터 operator와 같은 것 필터
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        // 찾아낸 enum 타입 객체가 가진 arithmeticCalculate(~) 메서드를 실행
        return arithmeticOperator.arithmeticCalculate(operand1, operand2);
    }
}
