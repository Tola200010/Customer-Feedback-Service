package feedbackservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateFeedbackRequestDto {
    @Min(1)
    @Max(5)
    private Integer rating;
    private String feedback;
    private String customer;
    @NotBlank
    private String product;
    @NotBlank
    private String vendor;

    public @Min(1) @Max(5) Integer getRating() {
        return rating;
    }

    public void setRating(@Min(1) @Max(5) Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public @NotBlank String getProduct() {
        return product;
    }

    public void setProduct(@NotBlank String product) {
        this.product = product;
    }

    public @NotBlank String getVendor() {
        return vendor;
    }

    public void setVendor(@NotBlank String vendor) {
        this.vendor = vendor;
    }
}
