package org.example.oopPractice.calculator.toBe;

import org.example.oopPractice.calculator.domain.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
