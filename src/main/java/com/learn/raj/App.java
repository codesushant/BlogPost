package com.learn.raj;

import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.Blog;
import com.learn.raj.entities.Comment;
import com.learn.raj.entities.CommentReply;
import com.learn.raj.entities.User;
import com.learn.raj.resources.BlogResource;
import com.learn.raj.resources.SampleResource;
import com.learn.raj.services.EmailService;
import com.learn.raj.services.impl.EmailServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<BlogPostConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    private final HibernateBundle<BlogPostConfiguration> hibernateBundle
            = new HibernateBundle<BlogPostConfiguration>(Blog.class, User.class, Comment.class, CommentReply.class) {
//        @Override
        public DataSourceFactory getDataSourceFactory(
                BlogPostConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

//    private final EntityManagerBundle<BlogPostConfiguration> entityManagerBundle =
//            new EntityManagerBundle<BlogPostConfiguration>(Blog.class) {
////                @Override
//                public DataSourceFactory getDataSourceFactory(BlogPostConfiguration configuration) {
//                    return configuration.getDataSourceFactory();
//                }
//            };

    public void initialize(Bootstrap<BlogPostConfiguration> bootstrap){
        bootstrap.addBundle(hibernateBundle);
//        bootstrap.addBundle(entityManagerBundle);

    }

    public void run(BlogPostConfiguration blogPostConfiguration, Environment environment) throws Exception {

//        final EntityManager entityManager = entityManagerBundle.getSharedEntityManager();

        final BlogDao blogDao = new BlogDao(hibernateBundle.getSessionFactory());
        final UserDao userDao = new UserDao(hibernateBundle.getSessionFactory());
        final CommentDao commentDao = new CommentDao(hibernateBundle.getSessionFactory());
        final CommentReplyDao commentReplyDao = new CommentReplyDao(hibernateBundle.getSessionFactory());
        final EmailService emailService = new EmailServiceImpl();
        final BlogResource blogResource = new BlogResource(blogDao, userDao, commentDao, commentReplyDao, emailService);
        environment.jersey().register(blogResource);
        environment.jersey().register(SampleResource.class);

//        environment.jersey().register(AuthFactory.binder(
//                new BasicAuthFactory<>(
//                        new BlogAuthenticator(),
//                        "SECURITY REALM",
//                        User.class)));

//        UserAuthenticatorProxy userAuthenticatorProxy = new UnitOfWorkAwareProxyFactory(hibernateBundle)
//                .create(UserAuthenticatorProxy.class, UserDao.class, userDao);

        BlogAuthenticator blogAuthenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(BlogAuthenticator.class, UserDao.class, userDao);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(blogAuthenticator)
//                        .setAuthorizer(new ExampleAuthorizer())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
