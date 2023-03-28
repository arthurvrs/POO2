import classes from "./review.module.css"

function Review(props) {
  return (
    <div>
      <div className={classes.div}>
        <div>Usuario:</div>
        <div className={classes.username}>{props.reviewerUsername}</div>
      </div>
      <div>{props.review}</div>
    </div>
  );
}

export default Review;
