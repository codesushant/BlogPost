package com.learn.raj.services;

import com.learn.raj.entities.Blog;
import com.learn.raj.entities.Comment;
import com.learn.raj.entities.CommentReply;
import com.learn.raj.entities.User;
import com.learn.raj.requests.*;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    Blog postBlog(BlogRequest blogRequest, User user) throws IOException;
    Blog editBlog(BlogUpdateRequest blogUpdateRequest, User user, long blogId);
    List<Blog> fetchAllBlogs();
    Blog fetchBlog(long blogId);
    User saveUser(UserRegisterRequest userRegisterRequest);
    List<User> fetchAllUsers();
    User getUser(String username);
    User getUser(long userId);
    Blog postComment(CommentRequest commentRequest, long blogId);
    List<Comment> getCommentFromBlog(long blogId);
    Comment postReply(ReplyRequest replyRequest, long commentId);
    List<CommentReply> getReplyFromComment(long commentId);
}
