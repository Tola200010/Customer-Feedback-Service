package feedbackservice.repository;

import feedbackservice.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback,String>,CustomFeedbackRepository {
    Page<Feedback> findAllByOrderByIdDesc(Pageable pageable);
}
