import { useContext, useRef } from "react";
import axios from "axios";

import classes from "../layout/input.module.css";
import UserContext from "../user-context";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const usernameInputRef = useRef();
  const passwordInputRef = useRef();

  const UserInfo = useContext(UserContext);
  const navigate = useNavigate();

  const SubmitHandler = (event) => {
    event.preventDefault();
    const usernameData = usernameInputRef.current.value;
    const passwordData = passwordInputRef.current.value;

    const usuario = {
      username: usernameData,
      senha: passwordData,
    };
    axios
      .post("http://localhost:8080/usuario/login", usuario)
      .then((response) => {
        if (response.data !== "nenhum") {
          console.log(usernameData, response.data);
          UserInfo.login(usernameData, response.data);
          navigate("/", { replace: true });
        } else {
          console.log("Usuario ou senha invalidos");
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
          <label htmlFor="title">Nome de usuario:</label>
          <input type="text" ref={usernameInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Senha:</label>
          <input type="text" ref={passwordInputRef} />
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

export default Login;
