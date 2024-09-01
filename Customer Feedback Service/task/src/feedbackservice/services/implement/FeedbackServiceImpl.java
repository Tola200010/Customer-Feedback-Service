package feedbackservice.services.implement;

import feedbackservice.dto.CreateFeedbackRequestDto;
import feedbackservice.dto.FeedbackPageResponseDto;
import feedbackservice.entity.Feedback;
import feedbackservice.exception.NotFoundException;
import feedbackservice.repository.FeedbackRepository;
import feedbackservice.services.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackServiceImpl(FeedbackRepository repository) {
        this.repository = repository;
    }

    @Override
    public Feedback createNewFeedback(CreateFeedbackRequestDto requestDto) {
        Feedback feedback = new Feedback();
        feedback.setRating(requestDto.getRating());
        feedback.setFeedback(requestDto.getFeedback());
        feedback.setCustomer(requestDto.getCustomer());
        feedback.setProduct(requestDto.getProduct());
        feedback.setVendor(requestDto.getVendor());
        return repository.insert(feedback);
    }

    @Override
    public FeedbackPageResponseDto getAllFeedback(int page, int perPage, String rating, String customer, String product, String vendor) {
        page = Math.max(page, 1);
        if (perPage < 5 || perPage > 20) {
            perPage = 10;
        }
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Feedback> feedbackPage = repository.findByFilters(rating, customer, product, vendor, pageable);
        FeedbackPageResponseDto response = new FeedbackPageResponseDto();
        response.setTotalDocuments(feedbackPage.getTotalElements());
        response.setIs_first_page(feedbackPage.isFirst());
        response.setIs_last_page(feedbackPage.isLast());
        response.setDocuments(feedbackPage.getContent());
        return response;
    }

    @Override
    public Feedback getFeedbackById(String id) {
       return repository.findById(id).orElseThrow(()-> new NotFoundException("ID %s not found.".formatted(id)));
    }
}
