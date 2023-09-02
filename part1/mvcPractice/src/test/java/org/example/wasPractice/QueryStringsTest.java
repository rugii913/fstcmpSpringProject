package org.example.wasPractice;

import org.junit.jupiter.api.Test;

class QueryStringsTest { // QueryString을 기반으로 일급 컬렉션 QueryStrings를 만든다.

    @Test
    void createTest() {
        QueryStrings queryStrings = new QueryStrings("operand=11&operator=*&operand2=55"); // List<QueryString>
        assertThat(queryStrings).isNotNull();
    }

}