package com.learn.raj.dao;

import com.learn.raj.entities.Blog;
import com.learn.raj.requests.BlogRequest;
import com.learn.raj.requests.BlogUpdateRequest;
import com.learn.raj.entities.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BlogDao extends AbstractDAO<Blog> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public BlogDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Blog> fetchAll(){
        return list(namedQuery("com.learn.java.Blogs.findAll"));
    }

    public boolean postBlog(BlogRequest blogRequest, User user){
        Blog blogObj = new Blog(user.getUserId(), blogRequest.getTitle(), blogRequest.getContent(),blogRequest.getAuthorName(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 0);
        persist(blogObj);
        return true;
    }

    public Blog getBlog(long blogId){
        Blog blog = get(blogId);
        return blog;
    }

    public boolean saveBlog(Blog blog){
        persist(blog);
        return true;
    }

//    public List<Blog> getsome(){
//
//    }

    public boolean editBlog(BlogUpdateRequest blogUpdateRequest){

        Blog currentBlog = getBlog(blogUpdateRequest.getBlogId());
        currentBlog.setContent(blogUpdateRequest.getContent());
        currentBlog.setTitle(blogUpdateRequest.getTitle());
        persist(currentBlog);
        return true;
    }
}
