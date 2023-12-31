package com.example.FirstProject.service;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "아와왕", "아와와왕");
        Article b = new Article(2L, "하와왕", "하와와왕");
        Article c = new Article(3L, "노와왕", "노와와왕");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        //2. 실제 데이터
        List<Article> articleList = articleService.index();
        //3. 두 데이터 비교 및 검증
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_success_existID() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "아와왕", "아와와왕");
        // 2.  실제 데이터
        Article real = articleService.show(id);
        // 3. 비교!!
        assertEquals(expected.toString(), real.toString());
    }

    @Test
    void show_fail_InvalidID() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article  real = articleService.show(id);
        //3 비교
        assertEquals(expected, real);
    }

    @Test
    @Transactional
    void create_success() {
        // 1. 예상 데이터
        String title = "호와왕";
        String content = "호와와왕";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article created = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), created.toString());
    }

    @Test
    @Transactional
    void create_fail() {
        //1. 예상 데이터
        //id 가 들어가면 생성 실패임 -> id 는 자동 생성이니까
        Long id = 4L;
        String title = "라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. 실제 데이터 생성
        Article created = articleService.create(dto);
        //3. 비교
        assertEquals(expected, created);
    }


    @Test
    @Transactional
    void update_success1() {
        //1. 예상 데이터 작성하기
        Long id = 3L;
        String title = "우와왕";
        String content = "우와와왕";
        Article expected = new Article(id, title, content);
        ArticleForm temp = new ArticleForm(id, title, content);
        //2. 실제 데이터 수정하기
        Article updated = articleService.update(id, temp);
        //3. 비교하기
        assertEquals(expected.toString(), updated.toString());
    }

    @Test
    @Transactional
    void update_success2() {
        //1. make wrong data
        Long id = 3L;
        String title = "노와왕";
        String content = "우와와왕";
        Article expected = new Article(id, title, content);
        ArticleForm temp = new ArticleForm(id, title, content);
        //2. update
        Article updated = articleService.update(id, temp);
        //3. validate
        assertEquals(expected.toString(), updated.toString());
    }

    @Test
    @Transactional
    void update_fail() {
        Long id = 4L;
        String title = "오와왕";
        String content = "오와와왕";
        Article expected = null;
        ArticleForm temp = new ArticleForm(id, title, content);
        //2. update
        Article updated = articleService.update(id, temp);
        //3. validate
        assertEquals(expected, updated);
    }

    @Test
    @Transactional
    void delete_success() {
        //1. expected data
        Long id = 3L;
        Article article = articleService.show(id);
        Article deleted = articleService.delete(id);
        assertEquals(article.toString(), deleted.toString());
    }

    @Test
    @Transactional
    void delete_fail() {
        Long id = 4L;
        Article article = null;
        Article deleted = articleService.delete(id);
        assertEquals(article, deleted);
    }
}