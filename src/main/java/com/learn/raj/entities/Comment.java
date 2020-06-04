package com.learn.raj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "com.learn.java.Comment.findCommentByBlogId",
        query = "select u from Comment u where u.blogId = :blog_id")
})

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    private long blogId;
    private String comment_text;
    private Date createdTime;
//    @ManyToOne
//    private Blog blog;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId")
    private Set<CommentReply> replies;

    public Comment(long blogId, String comment_text, Date createdTime){
        this.blogId = blogId;
        this.comment_text = comment_text;
        this.createdTime = createdTime;
    }

    public Comment(String comment_text, Date createdTime){
        this.comment_text = comment_text;
        this.createdTime = createdTime;
    }
}
