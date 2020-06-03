package com.learn.raj.resources;


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
import com.learn.raj.services.impl.BlogServiceImlp;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/blog")
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource {
    private BlogService blogService;

    public BlogResource(BlogDao blogDao, UserDao userDao, CommentDao commentDao, CommentReplyDao commentReplyDao) {
        this.blogService = new BlogServiceImlp(blogDao, userDao, commentDao, commentReplyDao);
    }

    @POST
    @Path("/postBlog")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response storeBlog(BlogRequest blogRequest){
        blogService.postBlog(blogRequest);
        return Response.ok().build();
    }

    @POST
    @Path("/editBlog")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editBlog( @Auth User user,
                              BlogUpdateRequest blogUpdateRequest){
        User currentUser = blogService.getUser(user.getName());
        blogService.editBlog(currentUser, blogUpdateRequest);
        return Response.ok().build();
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
    @Path("/{blogId}")
    public Response getBlog(@PathParam("blogId") long blogId){
        Blog blog = blogService.fetchBlog(blogId);
        return Response.ok()
                .entity(blog)
                .build();
    }


    @GET
    @Path("/checkauth1")
    @UnitOfWork
    public Response checkAuth1(@Auth User user){
        User user1 = blogService.getUser(user.getUserName());
        return Response.ok()
                .entity(user1)
//                .entity(new String("Hello-world"))
                .build();
    }

//    @GET
//    @Path("/checkauth2")
//    @UnitOfWork
//    public Response checkAuth2(@Context SecurityContext context){
//
//        User user = (User) context.getUserPrincipal();
//
//        User user1 = blogService.getUser(user.getUserName());
//
//        User user2 = blogService.getUser(user.getName());
//        return Response.ok()
//                .entity(user1)
////                .entity(new String("Hello-world"))
//                .build();
//    }


    @POST
    @Path("/registerUser")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserRegisterRequest userRegisterRequest){
        blogService.saveUser(userRegisterRequest);
        return Response.ok().build();
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
    @Path("/user/{username}")
    public Response getUser(@PathParam("username") String name){
        User user = blogService.getUser(name);
        return Response.ok().entity(user).build();
    }

    @POST
    @UnitOfWork
    @Path("/postComment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postComment(CommentRequest commentRequest){
        blogService.postCommentOnBlog(commentRequest);
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    @Path("/comment/{blogId}")
    public Response getCommentFromBlog(@PathParam("blogId") long blogId){
        List<Comment> comments = blogService.getCommentFromBlog(blogId);
        return Response.ok()
                .entity(comments)
                .build();
    }

    @POST
    @UnitOfWork
    @Path("/postReply")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postReply(ReplyRequest replyRequest){
        blogService.postReplyOnComment(replyRequest);
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    @Path("/reply/{commentId}")
    public Response getReplyFromComment(@PathParam("commentId") long commentId){
        List<CommentReply> commentReplyList = blogService.getReplyFromComment(commentId);
        return Response.ok()
                .entity(commentReplyList)
                .build();
    }

}
