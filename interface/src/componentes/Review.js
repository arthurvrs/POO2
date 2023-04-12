import { useContext } from "react";
import classes from "./review.module.css";
import UserContext from "../user-context";
import axios from "axios";
import { useParams } from "react-router-dom";

function Review(props) {
  const UserInfo = useContext(UserContext);
  const { id } = useParams();

  const remover = (event) => {
    event.preventDefault();

    const user = {
      username: props.reviewerUsername,
    };

    axios.put(`http://localhost:8080/livro/remover-review/${id}`, user)
    .then((response) => {
      console.log(response.data);
    })
    .catch((error) => {
      console.log(error);
    })
  };

  return (
    <div className={classes.maindiv}>
      <div className={classes.div}>
        <div>{"Usuario:  " + props.reviewerUsername}</div>
        {UserInfo.username === props.reviewerUsername ||
        UserInfo.type === "admin" ? (
          <button onClick={remover}>Remover Review</button>
        ) : (
          <div />
        )}
      </div>
      <div>{props.review}</div>
    </div>
  );
}

export default Review;
