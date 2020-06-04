import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.Blog;
import com.learn.raj.entities.User;
import com.learn.raj.requests.BlogRequest;
import com.learn.raj.requests.EmailRequest;
import com.learn.raj.services.impl.BlogServiceImlp;
import com.learn.raj.services.impl.EmailServiceImpl;
import com.sendgrid.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class BlogServiceImplTest {
    private BlogDao blogDao;
    private UserDao userDao;
    private CommentDao commentDao;
    private CommentReplyDao commentReplyDao;
    private BlogServiceImlp blogServiceImlp;
    private EmailServiceImpl emailService;

    @Before
    public void setUp() {
        this.blogDao = Mockito.mock(BlogDao.class);
        this.userDao = Mockito.mock(UserDao.class);
        this.commentDao = Mockito.mock(CommentDao.class);
        this.commentReplyDao  = Mockito.mock(CommentReplyDao.class);
        this.emailService = Mockito.mock(EmailServiceImpl.class);
        this.blogServiceImlp = new BlogServiceImlp(blogDao, userDao, commentDao, commentReplyDao, emailService);
    }

//    @Test(expected = BlogException.class)
    public void testPostBlog() throws IOException {
        BlogRequest blogRequest = BlogRequest.builder()
                .authorName("test_author")
                .content("")
                .title("test_title")
                .build();

        User user = User.builder()
                .userName("sushant.raj")
                .userId(1)
                .build();

        Blog expected = Blog.builder()
                .authorName("test_author")
                .blogId(1)
                .noOfViews(0)
                .content("")
                .title("test_title")
                .createdTime(new Timestamp(new Date().getTime()))
                .updatedTime(new Timestamp(new Date().getTime()))
                .userId(1)
                .build();

        Response response = new Response();

        Mockito.when(blogDao.postBlog(blogRequest, user)).thenReturn(expected);
        Mockito.when(emailService.sendEmail(Mockito.any(EmailRequest.class))).thenReturn(response);
        Blog actual = blogServiceImlp.postBlog(blogRequest, user);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFetchAll(){
        blogServiceImlp.fetchAllBlogs();
        Mockito.verify(blogDao, Mockito.times(1)).fetchAll();
    }
}
