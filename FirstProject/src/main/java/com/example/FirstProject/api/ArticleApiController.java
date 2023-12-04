package com.example.FirstProject.api;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        //1. DTO to Entity
        Article newArticle = dto.toEntity();
        //2. Find there is entity in db
        Article originalEntity = articleRepository.findById(id).orElse(null);
        //3. Check is null or find data.
        if (originalEntity == null || !id.equals(newArticle.getId())){
            log.info("Wrong request ID : {}, article : {}", id, newArticle.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. Update!
        newArticle.patch(originalEntity);
        Article updated = articleRepository.save(newArticle);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. 대상 찾기
        Article targetEntity = articleRepository.findById(id).orElse(null);
        // 2. 삭제!
        if(targetEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. return response
        articleRepository.delete(targetEntity);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
