package com.example.FirstProject.dto;


import com.example.FirstProject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CommentDto {

    private Long id;
    //@JsonProperty("article_id") -> Do not set value because this field annotation JsonProeprty??
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
