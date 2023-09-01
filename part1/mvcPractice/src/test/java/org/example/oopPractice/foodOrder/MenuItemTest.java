package org.example.oopPractice.foodOrder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MenuItemTest {

    @Test
    @DisplayName("메뉴 항목을 생성한다.")
    void createTest() {
        assertThatCode(() -> new MenuItem("만두", 5000)).doesNotThrowAnyException();
    }
}
