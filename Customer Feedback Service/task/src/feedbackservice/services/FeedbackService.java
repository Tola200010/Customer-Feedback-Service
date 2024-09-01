package feedbackservice.services;

import feedbackservice.dto.CreateFeedbackRequestDto;
import feedbackservice.dto.FeedbackPageResponseDto;
import feedbackservice.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createNewFeedback(CreateFeedbackRequestDto requestDto);
    FeedbackPageResponseDto getAllFeedback(int page, int perPage, String rating, String customer, String product, String vendor);
    Feedback getFeedbackById(String id);
}
