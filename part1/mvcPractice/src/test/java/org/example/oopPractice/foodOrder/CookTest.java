package org.example.oopPractice.foodOrder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CookTest {
    @Test
    @DisplayName("요리를 생성한다.")
    void createTest() {
        assertThatCode(() -> new Cook("만두", 5000)).doesNotThrowAnyException();
    }
}