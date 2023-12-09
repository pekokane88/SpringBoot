package com.example.FirstProject.repository;

import com.example.FirstProject.entity.Article;
import com.example.FirstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // 1. Case1 : 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "park", "다크나이트");
            Comment b = new Comment(2L, article, "lee", "인셉션");
            Comment c = new Comment(3L, article, "choi", "쇼생크 탈추루");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        // 2. Case2 : 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "아와왕", "아와와왕");
            List<Comment> expected = Arrays.asList();
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없어요!");
        }
        // 3. Case2 : 9번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(9L, "아와왕", "아와와왕");
            List<Comment> expected = Arrays.asList();
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없어요!");
        }
        // 4. Case2 : 999번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(999L, "아와왕", "아와와왕");
            List<Comment> expected = Arrays.asList();
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없어요!");
        }
        // 5. Case2 : -1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(-1L, "아와왕", "아와와왕");
            List<Comment> expected = Arrays.asList();
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없어요!");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // 1. Case1 : PARK 씨의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "park";
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname,"다크나이트");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname,"치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname,"조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "Park 의 모든 댓글을 출력!");
        }
        // 2. Case2 : Kim 씨의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "kim";
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname,"다크나이트");
            Comment b = new Comment(2L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname,"치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname,"조깅");
            List<Comment> expected = Arrays.asList();
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "Kim 의 모든 댓글을 출력!");
        }
        // 3. Case3 : null 씨의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = null;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname,"다크나이트");
            Comment b = new Comment(2L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname,"치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname,"조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "Kim 의 모든 댓글을 출력!");
        }
        // 4. Case4 : "" 씨의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = null;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname,"다크나이트");
            Comment b = new Comment(2L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname,"치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname,"조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 검증
            assertEquals(expected.toString(), comments.toString(), "Kim 의 모든 댓글을 출력!");
        }
    }
}