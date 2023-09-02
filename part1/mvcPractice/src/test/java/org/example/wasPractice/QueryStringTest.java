package org.example.wasPractice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryStringTest {

    // operand1=11
    @Test
    void createTest() {
        QueryString queryString = new QueryString("operand1", "11");
        Assertions.assertThat(queryString).isNotNull();
    }
}
