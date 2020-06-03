package com.learn.raj.services.impl;

import com.learn.raj.*;
import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.Blog;
import com.learn.raj.entities.Comment;
import com.learn.raj.entities.CommentReply;
import com.learn.raj.entities.User;
import com.learn.raj.requests.*;
import com.learn.raj.services.BlogService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BlogServiceImlp implements BlogService {

    private final BlogDao blogDao;
    private final UserDao userDao;
    private final CommentDao commentDao;
    private  final CommentReplyDao commentReplyDao;

    public BlogServiceImlp(BlogDao blogDao, UserDao userDao, CommentDao commentDao, CommentReplyDao commentReplyDao) {
        this.blogDao = blogDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.commentReplyDao = commentReplyDao;
    }

    public boolean postBlog(BlogRequest blogRequest) {
        ValidationUtils.validateBlog(blogRequest);
        User user = userDao.findByName(blogRequest.getUserName()).get(0);
        blogDao.postBlog(blogRequest, user);
        return true;
    }

    public boolean editBlog(User user, BlogUpdateRequest blogUpdateRequest){

        Blog blog = blogDao.getBlog(blogUpdateRequest.getBlogId());
        if(user.getUserId() != blog.getUserId())
            throw BlogException.create("Incorrect User, cannot update blog", ResponseCode.INCORRECT_USER);
        blogDao.editBlog(blogUpdateRequest);
        return true;
    }

    public List<Blog> fetchAllBlogs() {
        List<Blog> blogs = blogDao.fetchAll();
        return blogs;
    }

    @Override
    public Blog fetchBlog(long blogId) {
        return blogDao.getBlog(blogId);
    }

    public boolean saveUser(UserRegisterRequest userRegisterRequest) {
        userDao.storeUser(userRegisterRequest);
        return false;
    }

    public List<User> fetchAllUsers(){
        List<User> users = userDao.fetchAll();
        return users;
    }

    @Override
    public User getUser(String username) {
        return userDao.findByName(username).get(0);
    }

    public boolean postCommentOnBlog(CommentRequest commentRequest){
//        commentDao.postComment(commentRequest);
        Comment comment = new Comment(commentRequest.getCommentText(), new Timestamp(new Date().getTime()));
        Blog blog = blogDao.getBlog(commentRequest.getBlogId());
        blog.getComments().add(comment);
        blogDao.saveBlog(blog);
        return true;
    }

    public List<Comment> getCommentFromBlog(long blogId){
        List<Comment> commentList = commentDao.getCommentByBlogId(blogId);
        return commentList;
    }

    public boolean postReplyOnComment(ReplyRequest replyRequest){

        CommentReply commentReply = new CommentReply(replyRequest.getReplyText(), new Timestamp(new Date().getTime()));
        Comment comment = commentDao.getComment(replyRequest.getCommentId());
        comment.getReplies().add(commentReply);
        commentDao.saveComment(comment);
//        commentReplyDao.postReply(replyRequest);
        return true;
    }

    public List<CommentReply> getReplyFromComment(long commentId){
        List<CommentReply> commentReplyList = commentReplyDao.getReplyByCommentId(commentId);
        return commentReplyList;
    }
}
