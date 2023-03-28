import Review from "./Review";

function ListaReviews(props) {
  return (
    <div>
      {props.reviews.map((review) => (
        <Review 
            reviewerUsername={review.reviewerUsername}
            review={review.review}
         />
      ))}
    </div>
  );
}

export default ListaReviews;