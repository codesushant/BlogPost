package com.learn.raj.resources;


import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.Blog;
import com.learn.raj.entities.Comment;
import com.learn.raj.entities.User;
import com.learn.raj.requests.*;
import com.learn.raj.services.BlogService;
import com.learn.raj.services.EmailService;
import com.learn.raj.services.impl.BlogServiceImlp;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/blog-post")
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource {
    private BlogService blogService;

    public BlogResource(BlogDao blogDao, UserDao userDao,
                        CommentDao commentDao, CommentReplyDao commentReplyDao,
                        EmailService emailService) {
        this.blogService = new BlogServiceImlp(blogDao, userDao,
                commentDao, commentReplyDao,
                emailService);
    }

    @POST
    @Path("/blogs")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postBlog(@Auth User user,
                             @Valid BlogRequest blogRequest) throws IOException {
        Blog blog = blogService.postBlog(blogRequest, user);
        return Response.ok()
                .entity(blog)
                .build();
    }

    @PUT
    @Path("/blogs/{blogId}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editBlog( @Auth User user,
                              @PathParam("blogId") long blogID,
                              BlogUpdateRequest blogUpdateRequest){
        User currentUser = blogService.getUser(user.getName());
        Blog blog = blogService.editBlog(blogUpdateRequest, currentUser, blogID);
        return Response.ok()
                .entity(blog)
                .build();
    }

    @GET
    @UnitOfWork
    @Path("/blogs")
    public Response getBlogs(){
        List<Blog> blogList = blogService.fetchAllBlogs();
        return Response.ok()
                .entity(blogList)
                .build();
    }

    @GET
    @UnitOfWork
    @Path("/blogs/{blogId}")
    public Response getBlog(@PathParam("blogId") long blogId){
        Blog blog = blogService.fetchBlog(blogId);
        blog = blogService.updateViews(blog);
        return Response.ok()
                .entity(blog)
                .build();
    }

    @POST
    @Path("/users")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@Valid UserRegisterRequest userRegisterRequest){
        User user = blogService.saveUser(userRegisterRequest);
        return Response.ok()
                .entity(user)
                .build();
    }

    @GET
    @UnitOfWork
    @Path("/users")
    public Response getUsers() {
        List<User> userList = blogService.fetchAllUsers();
        return Response.ok()
                .entity(userList)
                .build();
    }

    @GET
    @UnitOfWork
    @Path("/users/{userId}")
    public Response getUser(@PathParam("userId") long userId){
        User user = blogService.getUser(userId);
        return Response.ok().entity(user).build();
    }

    @POST
    @UnitOfWork
    @Path("/blogs/{blogId}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postComment(@PathParam("blogId") long blogId,
                                @Valid CommentRequest commentRequest){
        Blog blog = blogService.postComment(commentRequest, blogId);
        return Response.ok()
                .entity(blog)
                .build();
    }

    @POST
    @UnitOfWork
    @Path("/comments/{commentId}/replies")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postReply(@PathParam("commentId") long commentId,
                              ReplyRequest replyRequest){
        Comment comment = blogService.postReply(replyRequest, commentId);
        return Response.ok()
                .entity(comment)
                .build();
    }

    @GET
    @Path("/check-auth")
    @UnitOfWork
    public Response checkAuth(@Auth User user){
        User currentUser = blogService.getUser(user.getUserName());
        return Response.ok()
                .entity(currentUser)
                .build();
    }


//    @GET
//    @UnitOfWork
//    @Path("/comment/{blogId}")
//    public Response getCommentFromBlog(@PathParam("blogId") long blogId){
//        List<Comment> comments = blogService.getCommentFromBlog(blogId);
//        return Response.ok()
//                .entity(comments)
//                .build();
//    }

//    @GET
//    @UnitOfWork
//    @Path("/reply/{commentId}")
//    public Response getReplyFromComment(@PathParam("commentId") long commentId){
//        List<CommentReply> commentReplyList = blogService.getReplyFromComment(commentId);
//        return Response.ok()
//                .entity(commentReplyList)
//                .build();
//    }

}
