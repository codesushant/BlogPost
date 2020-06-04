import com.learn.raj.BlogAuthenticator;
import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.User;
import com.learn.raj.resources.BlogResource;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BasicAuthResourceTest {

    private static final BlogDao blogDao = Mockito.mock(BlogDao.class);
    private static final UserDao userDao = Mockito.mock(UserDao.class);
    private static final CommentDao commentDao = Mockito.mock(CommentDao.class);
    private static final CommentReplyDao commentReplyDao = Mockito.mock(CommentReplyDao.class);

//    private static final BlogAuthenticator blogAuthenticator = new UnitOfWorkAwareProxyFactory()
//                .create(BlogAuthenticator.class, UserDao.class, userDao);

//    private static final BasicCredentialAuthFilter<User> BASIC_AUTH_HANDLER =
//            new BasicCredentialAuthFilter.Builder<User>()
//            .setAuthenticator(new BlogAuthenticator(userDao))
//            .setRealm("SUPER SECRET STUFF")
//                        .buildAuthFilter();
//    private static final ResourceTestRule resourceExtension = ResourceTestRule
//            .builder()
//            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
//            .addProvider(new AuthDynamicFeature(BASIC_AUTH_HANDLER))
//            .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
//            .addProvider(BlogResource.class)
//            .addResource(new BlogResource(blogDao, userDao, commentDao, commentReplyDao))
//            .build();


//    @Before
//    public void setUp() {
//        this.userDao = Mockito.mock(UserDao.class);
//        this.BASIC_AUTH_HANDLER =
//                new BasicCredentialAuthFilter.Builder<User>()
//                        .setAuthenticator(new BlogAuthenticator(userDao))
////                        .setAuthorizer(new ExampleAuthorizer())
////                        .setPrefix("Basic")
//                        .setRealm("SUPER SECRET STUFF")
//                        .buildAuthFilter();
//
//        this.resourceExtension = ResourceExtension
//                .builder()
//                .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
//                .addProvider(new AuthDynamicFeature(this.BASIC_AUTH_HANDLER))
//                .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
//                .addResource(BlogResource.class)
//                .build();
//
////        public ResourceExtension resourceExtension = ResourceExtension
////                .builder()
////                .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
////                .addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
////                        .setAuthenticator(new MyBasicAuthenticator())
////                        .setAuthorizer(new MyBasicAuthorizer())
////                        .buildAuthFilter()))
////                .addProvider(RolesAllowedDynamicFeature.class)
////                .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
////                .addResource(new ProtectedResource())
////                .build()
//
//    }

//    @Test
//    public void testProtectedResource(){
//
////        String credential = "Basic " + Base64.getEncoder().encodeToString("test@gmail.com:secret".getBytes());
//
////        User credential = new User("sushant.raj", "1234");
////        System.out.println(credential);
//
//        Response response = resourceExtension
//                .target("/blog/checkauth1")
//                .request()
//                .header(HttpHeaders.AUTHORIZATION, "sushant.raj:1234")
//                .get(Response.class);
//        System.out.println(response);
//
////        Response response = resourceExtension
////                .target("/blog/checkauth1")
////                .request()
////                .header(HttpHeaders.AUTHORIZATION, credential)
////                .get(Response.class);
////
////        Assert.assertEquals(200, response.getStatus());
//
//    }
}
