import com.learn.raj.BlogException;
import com.learn.raj.requests.BlogRequest;
import com.learn.raj.entities.User;
import com.learn.raj.dao.BlogDao;
import com.learn.raj.dao.CommentDao;
import com.learn.raj.dao.CommentReplyDao;
import com.learn.raj.dao.UserDao;
import com.learn.raj.services.impl.BlogServiceImlp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BlogServiceImplTest {
    private BlogDao blogDao;
    private UserDao userDao;
    private CommentDao commentDao;
    private CommentReplyDao commentReplyDao;
    private BlogServiceImlp blogServiceImlp;

    @Before
    public void setUp() {
        this.blogDao = Mockito.mock(BlogDao.class);
        this.userDao = Mockito.mock(UserDao.class);
        this.commentDao = Mockito.mock(CommentDao.class);
        this.commentReplyDao  = Mockito.mock(CommentReplyDao.class);
        this.blogServiceImlp = new BlogServiceImlp(blogDao, userDao, commentDao, commentReplyDao);
    }

    @Test(expected = BlogException.class)
    public void testPostBlog(){
        BlogRequest blogRequest = BlogRequest.builder()
                .authorName("test_author")
                .content("")
                .title("test_title")
                .userName("test_user")
                .build();

        User user = User.builder()
                .build();

        Mockito.when(blogDao.postBlog(blogRequest, user)).thenReturn(true);
        blogServiceImlp.postBlog(blogRequest);
    }

    @Test
    public void testFetchAll(){
        blogServiceImlp.fetchAllBlogs();
        Mockito.verify(blogDao, Mockito.times(1)).fetchAll();
    }
}
