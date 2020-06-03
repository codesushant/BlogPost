package com.learn.raj.services;

import com.learn.raj.entities.Blog;
import com.learn.raj.entities.Comment;
import com.learn.raj.entities.CommentReply;
import com.learn.raj.entities.User;
import com.learn.raj.requests.*;

import java.util.List;

public interface BlogService {
    boolean postBlog(BlogRequest blogRequest);
    boolean editBlog(User user, BlogUpdateRequest blogUpdateRequest);
    List<Blog> fetchAllBlogs();
    Blog fetchBlog(long blogId);
    boolean saveUser(UserRegisterRequest userRegisterRequest);
    List<User> fetchAllUsers();
    User getUser(String username);
    boolean postCommentOnBlog(CommentRequest commentRequest);
    List<Comment> getCommentFromBlog(long blogId);
    boolean postReplyOnComment(ReplyRequest replyRequest);
    List<CommentReply> getReplyFromComment(long commentId);
}
