import axios from "axios";
import { useContext, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import UserContext from "../user-context";
import classes from "../layout/input.module.css";
import ErrorMessage from "../componentes/ErrorMessage";

function CadastroLivro() {
  const UserInfo = useContext(UserContext);
  const reviewInputRef = useRef();
  const { id } = useParams();
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("ok");

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
        setErrorMessage("Usuario inválido");
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
        {errorMessage !== "ok" ? (
          <ErrorMessage>{errorMessage}</ErrorMessage>
        ) : (
          <div />
        )}
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
