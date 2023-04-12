import { useContext, useRef, useState } from "react";
import axios from "axios";

import classes from "../layout/input.module.css";
import UserContext from "../user-context";
import { Link, useNavigate } from "react-router-dom";
import ErrorMessage from "../componentes/ErrorMessage";

function Senha() {
  const newpasswordInputRef = useRef();
  const passwordInputRef = useRef();

  const UserInfo = useContext(UserContext);
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("ok");

  const SubmitHandler = (event) => {
    event.preventDefault();
    const newpasswordData =  newpasswordInputRef.current.value;
    const passwordData = passwordInputRef.current.value;
    if (newpasswordData === "" || passwordData === "") {
      setErrorMessage("Campo não Preenchido");
    } else {
    const usuario = {
      username: UserInfo.username,
      senha: passwordData,
    };
    axios
      .post(`http://localhost:8080/usuario/senha/${newpasswordData}`, usuario)
      .then((response) => {
        console.log(response.data);
        if (response.data === "ok") {
          navigate("/", { replace: true });
        } 
      })
      .catch((error) => {
        console.log(error);
        setErrorMessage("Senha incorreta");
      });
  }};

  return (
    <div>
      <div>Login</div>
      <form className={classes.form} onSubmit={SubmitHandler}>
        <div className={classes.div}>
          <label htmlFor="title">Nova senha:</label>
          <input type="password" ref={newpasswordInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Confirme a senha atual:</label>
          <input type="password" ref={passwordInputRef} />
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

export default Senha;
