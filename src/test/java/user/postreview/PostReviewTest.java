package user.postreview;

import entity.Review;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.IInternshipDAO;
import service.dao.IReviewDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PostReviewTest {

    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private IInternshipDAO internshipDAO;
    private PostReview postReview;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        postReview = new PostReview(reviewDAO, internshipDAO);
        jdbcTemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbcTemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DROP TABLE reviews");
    }

    @Test
    public void testPostReviewWithValidRequestModel() {
        PostReviewRequest request = new PostReviewRequest(
                "I love Apple",
                "Leo",
                5
        );
        PostReviewResponse response = postReview.addReviewToCorporate(request);
        assertEquals(response.getStatus(), ServerStatus.SUCCESS);
        assertEquals(response.getMessage(), "You have successfully posted a review to reviewId 1");

        Review review = reviewDAO.getReview("1");
        assertEquals(review.getUserId(), "Leo");
        assertEquals(review.getContent(), "I love Apple");
        assertEquals(review.getRating(), 5);
    }
}