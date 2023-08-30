package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("패스워드를 초기화한다.")
    void passwordTest() {
        // given
        User user = new User();

        // when
        user.initPassword();

        // then
        Assertions.assertThat(user.getPassword()).isNotNull();
    }
}