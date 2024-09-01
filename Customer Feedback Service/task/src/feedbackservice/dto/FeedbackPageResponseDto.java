package feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import feedbackservice.entity.Feedback;

import java.util.List;

public class FeedbackPageResponseDto {
    @JsonProperty("total_documents")
    private long totalDocuments;
    @JsonProperty("is_first_page")
    private boolean is_first_page;
    @JsonProperty("is_last_page")
    private boolean is_last_page;
    private List<Feedback> documents;

    public long getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(long totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    public boolean isIs_first_page() {
        return is_first_page;
    }

    public void setIs_first_page(boolean is_first_page) {
        this.is_first_page = is_first_page;
    }

    public boolean isIs_last_page() {
        return is_last_page;
    }

    public void setIs_last_page(boolean is_last_page) {
        this.is_last_page = is_last_page;
    }

    public List<Feedback> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Feedback> documents) {
        this.documents = documents;
    }
}
