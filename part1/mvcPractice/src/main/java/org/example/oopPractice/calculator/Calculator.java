package org.example.oopPractice.calculator;

import org.example.oopPractice.calculator.operators.ArithmeticOperator;

public class Calculator {

    public static int calculate(int operand1, String operator, int operand2) {
        return ArithmeticOperator.calculate(operand1, operator, operand2);
    }
}
