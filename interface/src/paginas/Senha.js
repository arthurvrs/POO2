import { useContext, useRef } from "react";
import axios from "axios";

import classes from "../layout/input.module.css";
import UserContext from "../user-context";
import { Link, useNavigate } from "react-router-dom";

function Senha() {
  const newpasswordInputRef = useRef();
  const passwordInputRef = useRef();

  const UserInfo = useContext(UserContext);
  const navigate = useNavigate();

  const SubmitHandler = (event) => {
    event.preventDefault();
    const newpasswordData =  newpasswordInputRef.current.value;
    const passwordData = passwordInputRef.current.value;

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
      });
  };

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
