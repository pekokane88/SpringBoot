package com.example.FirstProject.service;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article newArticle =  dto.toEntity();
        if(newArticle.getId() != null){
            return  null;
        }
        return articleRepository.save(newArticle);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO to Entity
        Article newArticle = dto.toEntity();
        log.info("New Article ID : {}, article : {}", id, newArticle.toString());
        //2. Find there is entity in db
        Article originalEntity = articleRepository.findById(id).orElse(null);
        //3. Check is null or find data.
        if (originalEntity == null || !id.equals(newArticle.getId())){
            log.info("Wrong request ID : {}, article : {}", id, newArticle.toString());
            return null;
        }
        originalEntity.patch(newArticle);
        return articleRepository.save(originalEntity);
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article targetEntity = articleRepository.findById(id).orElse(null);
        // 2. 삭제!
        if(targetEntity == null){
            return null;
        }
        // 3. return response
        articleRepository.delete(targetEntity);

        return targetEntity;
    }

    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. Dto List 를 각각 Entity 로 변환
            List<Article> articleList = dtos.stream().map(ArticleForm::toEntity).toList();
        //2. 각 Entity 를 DB 에 저장.
            articleList.stream().forEach(article -> articleRepository.save(article));
        //3. 강제 Exception 발생?
            articleRepository.findById(-1L).orElseThrow( () -> new IllegalArgumentException("Fail!!"));
        //4 return
        return articleList;
    }
}
