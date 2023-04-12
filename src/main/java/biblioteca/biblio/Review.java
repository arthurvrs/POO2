package biblioteca.biblio;

public class Review {
    private String review;
    private String reviewerUsername;

    public Review(String review, String username){
        this.review = review;
        reviewerUsername = username;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void printReview () {
        System.out.println(reviewerUsername);
        System.out.println(review);
    }
}
