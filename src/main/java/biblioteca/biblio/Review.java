package biblioteca.biblio;

public class Review {
    public String review;
    public String reviewerUsername;

    public Review(String review, String username){
        this.review = review;
        reviewerUsername = username;
    }

    public void printReview () {
        System.out.println(reviewerUsername);
        System.out.println(review);
    }
}
