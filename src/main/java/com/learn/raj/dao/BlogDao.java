package com.learn.raj.dao;

import com.learn.raj.entities.Blog;
import com.learn.raj.requests.BlogRequest;
import com.learn.raj.requests.BlogUpdateRequest;
import com.learn.raj.entities.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.eclipse.jetty.util.StringUtil;
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

    public Blog postBlog(BlogRequest blogRequest, User user){
        Blog blogObj = new Blog(user.getUserId(), blogRequest.getTitle(), blogRequest.getContent(),blogRequest.getAuthorName(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 0);
        blogObj = saveBlog(blogObj);
        return blogObj;
    }

    public Blog getBlog(long blogId){
        Blog blog = get(blogId);
        return blog;
    }

    public Blog saveBlog(Blog blog){
        persist(blog);
        return blog;
    }

    public Blog updateViews(Blog blog){
        blog.setNoOfViews(blog.getNoOfViews()+1);
        persist(blog);
        return blog;
    }

    public Blog editBlog(BlogUpdateRequest blogUpdateRequest, Blog blog){
        if(!StringUtil.isEmpty(blogUpdateRequest.getContent()))
            blog.setContent(blogUpdateRequest.getContent());
        if(!StringUtil.isEmpty(blogUpdateRequest.getTitle()))
            blog.setTitle(blogUpdateRequest.getTitle());
        blog.setUpdatedTime(new Timestamp(new Date().getTime()));
        persist(blog);
        return blog;
    }
}
