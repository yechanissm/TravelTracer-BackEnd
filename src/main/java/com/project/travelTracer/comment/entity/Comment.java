package com.project.travelTracer.comment.entity;

import com.project.travelTracer.Post.entity.Post;
import com.project.travelTracer.global.time.BaseTimeEntity;
import com.project.travelTracer.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name="COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "writer_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Lob
    @Column(nullable = false)
    private String content;

    private boolean isRemoved = false;

    //== 부모 댓글을 삭제해도 자식 댓글은 남아있음 ==//
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Member writer) {
        this.writer = writer;
        writer.addComment(this);
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        childList.add(child);
    }
    //== 수정 ==//
    public void updateContent(String content) {
        this.content = content;
    }
    //== 삭제 ==//
    public void remove() {
        this.isRemoved = true;
    }


    @Builder
    public Comment( Member writer, Post post, Comment parent, String content) {
        this.writer = writer;
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.isRemoved = false;
    }
    public List<Comment> findRemovableList() {
        List<Comment> result = new ArrayList<>();
        Optional.ofNullable(this.parent).ifPresentOrElse(
                parentComment -> { //댓글인지 대댓글인지 확인
                    if(parentComment.isRemoved() && parentComment.isAllChildRemoved()){
                        result.addAll(parentComment.getChildList());
                        result.add(parentComment);
                    }
                },

                () -> {
                    if(isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildList());
                    }
                }
        );
        return result;
    }

    private boolean isAllChildRemoved() {
        return getChildList().stream()
                .map(Comment::isRemoved)
                .filter(isRemoved -> !isRemoved)
                .findAny()
                .orElse(true);
    }

}
