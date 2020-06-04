import com.learn.raj.resources.SampleResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
public class SampleResourceTest {

    @Rule
    public final ResourceTestRule resource = ResourceTestRule
            .builder()
            .addResource(new SampleResource())
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();

    @Test
    public void testSampleGet(){
        String result = resource/*.client()*/
                .target("/sample/test")
                .request()
                .get(String.class);
        String expected = "Welcome Stranger";
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testSampleOptional(){
        String result = resource/*.client()*/
                .target("/sample/raj")
                .request()
                .get(String.class);
        String expected = "Welcome raj";
        Assert.assertEquals(result, expected);
    }
}
