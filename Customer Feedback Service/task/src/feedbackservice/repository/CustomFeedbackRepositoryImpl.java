package feedbackservice.repository;

import feedbackservice.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository
public class CustomFeedbackRepositoryImpl implements CustomFeedbackRepository{
    private final MongoTemplate mongoTemplate;

    public CustomFeedbackRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Feedback> findByFilters(String rating, String customer, String product, String vendor, Pageable pageable) {
        Query query = new Query().with(pageable).with(Sort.by(Sort.Direction.DESC, "id"));
        if (rating != null) {
            query.addCriteria(Criteria.where("rating").is(Integer.parseInt(rating)));
        }
        if (customer != null) {
            query.addCriteria(Criteria.where("customer").is(customer));
        }
        if (product != null) {
            query.addCriteria(Criteria.where("product").is(product));
        }
        if (vendor != null) {
            query.addCriteria(Criteria.where("vendor").is(vendor));
        }

        List<Feedback> feedbacks = mongoTemplate.find(query, Feedback.class);
        long count = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Feedback.class);  // Get total count

        return new PageImpl<>(feedbacks, pageable, count);
    }
}
