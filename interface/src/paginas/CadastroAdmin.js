import axios from "axios";
import { useContext, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import classes from "../layout/input.module.css";
import UserContext from "../user-context";
import ErrorMessage from "../componentes/ErrorMessage";

function CadastroAdmin() {
  const [errorMessage, setErrorMessage] = useState("ok");

  const usernameInputRef = useRef();
  const password1InputRef = useRef();
  const password2InputRef = useRef();
  const contatoInputRef = useRef();

  const navigate = useNavigate();
  const UserInfo = useContext(UserContext);

  if (UserInfo.type !== "admin") {
    navigate("/", { replace: true });
  } else {
    const SubmitHandler = (event) => {
      event.preventDefault();

      const usernameData = usernameInputRef.current.value;
      const contatoData = contatoInputRef.current.value;
      const password1Data = password1InputRef.current.value;
      const password2Data = password2InputRef.current.value;

      if (
        usernameData === "" ||
        contatoData === "" ||
        password1Data === "" ||
        password2Data === ""
      ) {
        setErrorMessage("Campo não preenchido");
      } else if (password1Data !== password2Data) {
        setErrorMessage("Senhas diferentes");
      } else {
        const usuario = {
          username: usernameData,
          senha: password1Data,
          contato: contatoData,
        };

        axios
          .post("http://localhost:8080/usuario/cadastro-admin", usuario)
          .then((response) => {
            if (response.data === "ok") {
              navigate("/", { replace: true });
            } else {
              console.log("Nome de usuario já existe");
              setErrorMessage("Nome de usuario já existe");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    };

    return (
      <div>
        <div>Cadastro</div>
        <form className={classes.form} onSubmit={SubmitHandler}>
          <div className={classes.div}>
            <label htmlFor="title">Nome de usuario:</label>
            <input type="text" ref={usernameInputRef} />
          </div>
          <div className={classes.div}>
            <label htmlFor="title">
              Informação para contato (E-mail/Telefone):
            </label>
            <input type="text" ref={contatoInputRef} />
          </div>
          <div className={classes.div}>
            <label htmlFor="title">Senha:</label>
            <input type="password" ref={password1InputRef} />
          </div>
          <div className={classes.div}>
            <label htmlFor="title">Confirmar Senha:</label>
            <input type="password" ref={password2InputRef} />
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
}

export default CadastroAdmin;
