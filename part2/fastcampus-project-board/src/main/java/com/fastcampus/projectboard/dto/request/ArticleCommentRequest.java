package com.fastcampus.projectboard.dto.request;

import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.UserAccountDto;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.ArticleComment}
 */
public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId,content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        // 인증 관련 부분이 필요하기 때문에 UserAccountDto는 매개변수로 받도록 열어놓은 것
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }
}