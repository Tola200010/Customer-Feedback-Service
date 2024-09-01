package feedbackservice.controller;

import feedbackservice.dto.CreateFeedbackRequestDto;
import feedbackservice.entity.Feedback;
import feedbackservice.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<?> createNewFeedback(@RequestBody @Valid CreateFeedbackRequestDto requestDto){
        Feedback newFeedback = feedbackService.createNewFeedback(requestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFeedback.getId())
                .toUri();
        URI relativeLocation = URI.create(location.getPath());

        return ResponseEntity.created(relativeLocation).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable String id){
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    public ResponseEntity<?> getAllFeedback(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage,
            @RequestParam(value = "rating", required = false) String rating,
            @RequestParam(value = "customer", required = false) String customer,
            @RequestParam(value = "product", required = false) String product,
            @RequestParam(value = "vendor", required = false) String vendor
    ){
        return ResponseEntity.ok(feedbackService.getAllFeedback(page,perPage,rating,customer,product,vendor));
    }

}
