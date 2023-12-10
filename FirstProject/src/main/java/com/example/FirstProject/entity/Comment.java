package com.example.FirstProject.entity;

import com.example.FirstProject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createComment(CommentDto dto, Article article){
        //예외 발생
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 존재합니다.");
        }
        if(!Objects.equals(dto.getArticleId(), article.getId())){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id와 일치하지 않습니다.");
        }
        //왜냐면 ID 는 자동생성임.
        return new Comment(
                null,
                article,
                dto.getNickname(),
                dto.getBody()
        );
        //엔티티 생성 및 반환
    }

    public void patch(CommentDto commentDto) {
        //예외 발생
        if(!Objects.equals(this.id, commentDto.getId())){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 ID 가 입력되어졌습니다.");
        }
        if(commentDto.getNickname() != null){
            this.nickname = commentDto.getNickname();
        }
        if(commentDto.getBody() != null){
            this.body = commentDto.getBody();
        }
        //객체 갱신
    }
}
