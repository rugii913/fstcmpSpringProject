package dev.be.feign.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 이 객체를 사용해서 JSON serialize, deserialize 작업이 발생할 때, 값이 없으면 해당 필드를 없애준다.
public class BaseRequestInfo {
    private String name;
    private Long age;
}

