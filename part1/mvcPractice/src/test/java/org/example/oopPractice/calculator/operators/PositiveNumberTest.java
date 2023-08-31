package org.example.oopPractice.calculator.operators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class PositiveNumberTest {

    @Test
    void createTest() {
        assertThatCode(() -> new PositiveNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 또는 음수를 전달할 수 없습니다.");
    }
}