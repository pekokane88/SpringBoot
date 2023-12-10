package com.example.FirstProject.api;

import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/commentApi/")
@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;
    
    //1. 댓글 조회
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임.
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2. 댓글 생성
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto commentDto){
        //서비스 위임.
        CommentDto createdDto = commentService.create(articleId, commentDto);
        //결과 응답
        return (createdDto != null) ? ResponseEntity.status(HttpStatus.OK).body(createdDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentDto);
    }
    //3. 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto commentDto){
        CommentDto updatedDto = commentService.update(id, commentDto);
        return (updatedDto != null) ? ResponseEntity.status(HttpStatus.OK).body(updatedDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentDto);
    }
    //4. 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto deleteDto = commentService.delete(id);
        return (deleteDto != null) ? ResponseEntity.status(HttpStatus.OK).body(deleteDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
