package com.example.FirstProject.controller;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. view 에 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 찾기.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //Model에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String creteArticle(ArticleForm form){
        log.info(form.toString());
        //1. DTO 를 entity 로 변환
        Article article = form.toEntity();
        //2. repository 를 이용하여 entity 를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        //1. DTO to Entity
        Article articleEntity = form.toEntity();
        log.info(form.toString());
        //2.Entity to DB
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //3.redirect page
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기 - 찾아서 없으면 변수에 null 대입.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }
}
