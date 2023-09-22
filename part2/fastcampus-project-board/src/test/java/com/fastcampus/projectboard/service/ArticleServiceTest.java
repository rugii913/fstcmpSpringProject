package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
// 스프링 부트의 슬라이스 테스트 기능을 사용하지 않고 작성, 스프링 어플리케이션 컨텍스트가 뜨지 않도록
// 대신 dependency가 추가되어야 하는 부분이 있다면 mocking을 하는 방식으로 접근
// => Mockito 사용(spring test 패키지에 이미 포함되어 있음)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut; // cf. sut = system under test 테스트 대상이라는 의미
    // mock을 주입하는 대상에 @InjectMocks 붙여줌
    @Mock private ArticleRepository articleRepository;
    // 그외 나머지 모든 mock은 @Mock을 붙여줌
    // cf. Mockito 아직까지 현재 버전에서는 @Mock은 생성자 주입 가능한데, @InjectMock은 생성자 주입 불가능함, 여기서는 둘 다 필드 주입으로 넣어줌

    /*
    각 게시글 페이지로 이동
    페이지네이션
    홈 버튼 -> 게시판 페이지로 리다이렉션
    정렬 기능
    */
    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그
        
        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        // Given

        // When
        ArticleDto article = sut.searchArticle(1L);

        // Then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null); // repository의 save(~) 메서드가 호출될 것

        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "hashtag"));

        // Then
        then(articleRepository).should().save(any(Article.class)); // save(~)가 호출됐는지
        // 다른 layer까지 내려가지 않음 - sociable test가 아닌 단순 solitary unit test이므로 어떤 데이터가 저장됐는지에 대해서는 검사하지 않는다.
    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

        // Then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class)); // 더 발전된 테스트를 만들 게 아니라면 사실 이 부분은 없어도 똑같이 동작함

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));
    }
}