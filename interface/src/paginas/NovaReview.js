import axios from "axios";
import { useContext, useRef } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import UserContext from "../user-context";
import classes from "../layout/input.module.css";

function CadastroLivro() {
  const UserInfo = useContext(UserContext);
  const reviewInputRef = useRef();
  const { id } = useParams();
  const navigate = useNavigate();

  const SubmitHandler = (event) => {
    event.preventDefault();

    const reviewData = reviewInputRef.current.value;

    const review = {
      review: reviewData,
      reviewerUsername: UserInfo.username,
    };

    axios
      .post(`http://localhost:8080/livro/review/${id}`, review)
      .then((response) => {
        if (response.data === "ok") {
          navigate("/", { replace: true });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <div>Nova Review</div>
      <form className={classes.form} onSubmit={SubmitHandler}>
        <div className={classes.div}>
          <label htmlFor="title">Review :</label>
          <textarea required rows="6" ref={reviewInputRef} />
        </div>
        <div className={classes.div}>
          <button type="submit">Confirmar</button>
          <Link to="/">
            <button>Cancelar</button>
          </Link>
        </div>
      </form>
    </div>
  );
}

export default CadastroLivro;
