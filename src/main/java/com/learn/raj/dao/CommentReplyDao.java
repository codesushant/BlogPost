package com.learn.raj.dao;

import com.learn.raj.entities.CommentReply;
import com.learn.raj.requests.ReplyRequest;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CommentReplyDao extends AbstractDAO<CommentReply> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public CommentReplyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean postReply(ReplyRequest replyRequest){
        CommentReply commentReply = new CommentReply(replyRequest.getCommentId(), replyRequest.getReplyText(), new Timestamp(new Date().getTime()));
        persist(commentReply);
        return true;
    }

    public List<CommentReply> getReplyByCommentId(long commentId){
        return list(namedQuery("com.learn.java.Comment.findReplyByCommentId")
        .setParameter("comment_Id", commentId));
    }

}
