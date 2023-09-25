package com.fastcampus.projectboard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5; // 페이지네이션 bar의 길이, 다른 방법도 있지만 상태값으로 해놓음

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages); // 아래 IntStream에서 rangeClosed(~)가 아니라 range(~) 사용하므로 - 1 필요 없음

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int currentBarLength() { // 당장 필요하진 않은 메서드지만 만들어본 것
        return BAR_LENGTH;
    }
}
