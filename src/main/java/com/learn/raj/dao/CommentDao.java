package com.learn.raj.dao;

import com.learn.raj.entities.Comment;
import com.learn.raj.requests.CommentRequest;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CommentDao extends AbstractDAO<Comment> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public CommentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean postComment(CommentRequest commentRequest){
        Comment comment = new Comment(commentRequest.getBlogId(), commentRequest.getCommentText(), new Timestamp(new Date().getTime()));
        persist(comment);
        return true;
    }

    public List<Comment> getCommentByBlogId(long blogId){
        return list(namedQuery("com.learn.java.Comment.findCommentByBlogId")
                .setParameter("blog_id", blogId));
    }

    public Comment getComment(long commendId){
        return get(commendId);
    }

    public boolean saveComment(Comment comment){
        persist(comment);
        return true;
    }
}
