package com.learn.raj.services.impl;

import com.learn.raj.BlogException;
import com.learn.raj.Constants;
import com.learn.raj.ResponseCode;
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
import com.learn.raj.services.EmailService;
import com.sendgrid.Response;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BlogServiceImlp implements BlogService {

    private final BlogDao blogDao;
    private final UserDao userDao;
    private final CommentDao commentDao;
    private  final CommentReplyDao commentReplyDao;
    private final EmailService emailService;

    public BlogServiceImlp(BlogDao blogDao,
                           UserDao userDao,
                           CommentDao commentDao,
                           CommentReplyDao commentReplyDao,
                           EmailService emailService) {
        this.blogDao = blogDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.commentReplyDao = commentReplyDao;
        this.emailService = emailService;
    }

    public Blog postBlog(BlogRequest blogRequest, User user) throws IOException {
        Blog blog = blogDao.postBlog(blogRequest, user);
        Response response = sendEmail(emailService, user.getEmail(),
                Constants.Email.subject, blogRequest.getContent());
        System.out.println(response.toString());
        return blog;
    }

    public Response sendEmail(EmailService emailService, String recipient,
                              String subject, String content) throws IOException {
        EmailRequest emailRequest = EmailRequest
                .builder()
                .from(Constants.Email.emailFrom)
                .to(recipient)
                .subject(subject)
                .content(content)
                .build();
        Response response = emailService.sendEmail(emailRequest);
        return response;
    }

    public Blog editBlog(BlogUpdateRequest blogUpdateRequest, User user, long blogId){

        Blog blog = blogDao.getBlog(blogId);
        if(user.getUserId() != blog.getUserId())
            throw BlogException.create("Incorrect User, cannot update blog", ResponseCode.INCORRECT_USER);
        blog = blogDao.editBlog(blogUpdateRequest, blog);
        return blog;
    }

    public List<Blog> fetchAllBlogs() {
        List<Blog> blogs = blogDao.fetchAll();
        return blogs;
    }

    @Override
    public Blog fetchBlog(long blogId) {
        return blogDao.getBlog(blogId);
    }

    public User saveUser(UserRegisterRequest userRegisterRequest) {
        User user = userDao.storeUser(userRegisterRequest);
        return user;
    }

    public List<User> fetchAllUsers(){
        List<User> users = userDao.fetchAll();
        return users;
    }

    @Override
    public User getUser(String username) {
        return userDao.findByName(username).get(0);
    }

    @Override
    public User getUser(long userId) {
        return userDao.getUser(userId);
    }

    public Blog postComment(CommentRequest commentRequest, long blogId){
        Comment comment = new Comment(commentRequest.getCommentText(), new Timestamp(new Date().getTime()));
        Blog blog = blogDao.getBlog(blogId);
        blog.getComments().add(comment);
        blog = blogDao.saveBlog(blog);
        return blog;
    }

    public List<Comment> getCommentFromBlog(long blogId){
        List<Comment> commentList = commentDao.getCommentByBlogId(blogId);
        return commentList;
    }

    public Comment postReply(ReplyRequest replyRequest, long commentId){

        CommentReply commentReply = new CommentReply(replyRequest.getReplyText(), new Timestamp(new Date().getTime()));
        Comment comment = commentDao.getComment(commentId);
        comment.getReplies().add(commentReply);
        comment = commentDao.saveComment(comment);
        return comment;
    }

    public List<CommentReply> getReplyFromComment(long commentId){
        List<CommentReply> commentReplyList = commentReplyDao.getReplyByCommentId(commentId);
        return commentReplyList;
    }
}
