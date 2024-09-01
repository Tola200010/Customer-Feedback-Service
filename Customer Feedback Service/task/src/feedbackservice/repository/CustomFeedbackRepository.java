package feedbackservice.repository;

import feedbackservice.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomFeedbackRepository {
    Page<Feedback> findByFilters(String rating, String customer, String product, String vendor, Pageable pageable);
}
