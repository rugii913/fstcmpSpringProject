package com.fastcampus.projectboard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("테스트 도구 - Form 데이터 인코더")
@Import({FormDataEncoder.class, ObjectMapper.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = FormDataEncoderTest.EmptyConfig.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Void.class)
// 강의 예제에서는 classes = Void.class로 되어있으나 테스트 실패 - 같은 내용 Q&A에 올라와 있음, 이 부분은 아무 일도 하지 않는 클래스를 넣어서 테스트가 프로젝트의 빈들을 읽지 못하도록 기본 동작을 막은 것
// Q&A 링크: https://fastcampus.co.kr/qna/211368/read/8875
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = void.class)
// cf. Void.class가 아니라 void.class로 두면 에러 없이 돌아감... 왜...? Void.class도 아니고 void.class는 무엇인가?
// - 나중에 다시 확인할 것: https://stackoverflow.com/questions/33069014/what-does-void-class-denote - reflection에서 반환 타입 void를 지칭함
public class FormDataEncoderTest {

    private final FormDataEncoder formDataEncoder;

    public FormDataEncoderTest(@Autowired FormDataEncoder formDataEncoder) {
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("객체를 넣으면, url encoding 된 form body data 형식의 문자열을 돌려준다.")
    @Test
    void givenObject_whenEncoding_thenReturnsFormEncodedString() {
        // Given
        TestObject obj = new TestObject(
                "This 'is' \"test\" string.",
                List.of("hello", "my", "friend").toString().replace(" ", ""),
                String.join(",", "hello", "my", "friend"),
                null,
                1234,
                3.14,
                false,
                BigDecimal.TEN,
                TestEnum.THREE
        );

        // When
        String result = formDataEncoder.encode(obj);

        // Then
        System.out.println(result); // 눈으로 확인하기 위해 넣어둠
        assertThat(result).isEqualTo(
                "str=This%20'is'%20%22test%22%20string." +
                        "&listStr1=%5Bhello,my,friend%5D" +
                        "&listStr2=hello,my,friend" +
                        "&nullStr" +
                        "&number=1234" +
                        "&floatingNumber=3.14" +
                        "&bool=false" +
                        "&bigDecimal=10" +
                        "&testEnum=THREE"
        );
    }

    record TestObject(
            String str,
            String listStr1,
            String listStr2,
            String nullStr,
            Integer number,
            Double floatingNumber,
            Boolean bool,
            BigDecimal bigDecimal,
            TestEnum testEnum
    ) {
    }

    enum TestEnum {
        ONE, TWO, THREE;
    }

    public static class EmptyConfig { // 테스트 통과를 위해 만든 빈 클래스
    }
}
