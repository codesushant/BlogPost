package com.learn.raj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "replies")

@NamedQueries({
        @NamedQuery(name ="com.learn.java.Comment.findReplyByCommentId",
        query = "select u from CommentReply u where u.commentId = :comment_Id")
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;

    private long commentId;
    private String comment_text;
    private Date createdTime;

    public CommentReply(long commentId, String comment_text, Date createdTime){
        this.commentId = commentId;
        this.comment_text = comment_text;
        this.createdTime = createdTime;
    }

    public CommentReply(String comment_text, Date createdTime){
        this.comment_text = comment_text;
        this.createdTime = createdTime;
    }
}
