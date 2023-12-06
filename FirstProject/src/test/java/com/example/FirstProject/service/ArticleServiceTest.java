package com.example.FirstProject.service;

import com.example.FirstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//    void show_fail_InvalidID() {
//    }
}