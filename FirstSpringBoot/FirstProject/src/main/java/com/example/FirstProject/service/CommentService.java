package com.example.FirstProject.service;

import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.entity.Comment;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        //1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        //2. 엔티티 to dto
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i<comments.size(); i++){
//            Comment item = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(item);
//            dtos.add(dto);
//        }
        //3. return
        return commentRepository.findByArticleId(articleId).stream().map(CommentDto::createCommentDto).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto commentDto) {
        //1. 게시글 조회 및 예외 상황 가정
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 ! 대상 게시글이 없습니다."));
        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(commentDto, article);
        //3. 댓글 엔티티를 DB 에 저장
        Comment created = commentRepository.save(comment);
        //4. DTO 를 변환해 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto commentDto) {
        //1. 게시글 조회 및 예외 상황 가정.
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 수정 대상인 댓글 id 가 존재하지 않습니다."));
        //2. 댓글 엔티티 수정
        target.patch(commentDto);
        //3. 수정된 댓글 엔티티를 DB 에 저장
        Comment updated = commentRepository.save(target);
        //4. return entity 2 dto
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        //1. 게시글 조회 및 예시 상황 가정.
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 삭제 대상인 댓글이 존재하지 않습니다."));
        //2. 삭제
        commentRepository.delete(target);
        //3. 반환을 위해 target 을 Entity 2 dto 로 변경
        return CommentDto.createCommentDto(target);
    }
}
