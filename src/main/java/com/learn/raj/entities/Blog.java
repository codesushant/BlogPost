package com.learn.raj.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="blogs")
@NamedQueries({
        @NamedQuery(name = "com.learn.java.Blogs.findAll",
                query = "select b from Blog b"),
        @NamedQuery(name = "com.learn.java.Blogs.updateBlog",
                query = "Update Blog b set b.content = :content, b.updatedTime = :updated_time where b.blogId = :blog_id"),
})

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long blogId;

    private long userId;

    private String title;

    private String content;

    private String authorName;

    private Date createdTime;

    private Date updatedTime;

    private int noOfViews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blogId")
    private Set<Comment> comments;

    public Blog(long userId, String title, String content, String authorName, Date createdTime, Date updatedTime, int noOfViews) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.noOfViews = noOfViews;
    }
}
