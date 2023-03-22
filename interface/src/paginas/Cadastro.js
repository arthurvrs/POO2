import axios from "axios";
import { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";

import classes from "../layout/input.module.css";

function Cadastro() {
  const usernameInputRef = useRef();
  const password1InputRef = useRef();
  const password2InputRef = useRef();
  const contatoInputRef = useRef();

  const navigate = useNavigate();

  const SubmitHandler = (event) => {
    event.preventDefault();

    const usernameData = usernameInputRef.current.value;
    const contatoData = contatoInputRef.current.value;
    const password1Data = password1InputRef.current.value;
    const password2Data = password2InputRef.current.value;

    if(password1Data === password2Data) {
      const usuario = {
        username: usernameData,
        senha: password1Data,
        contato: contatoData
      }

      axios
      .post("http://localhost:8080/usuario/cadastro", usuario)
      .then ((response) => {
        if(response.data === "ok") {
          navigate("/", { replace: true });
        } else {
          console.log("nome de usuario já existe")
        }
      })
      .catch ((error) => {
        console.log(error);
      })
    }
  }

  return (
    <div>
      <div>Cadastro</div>
      <form className={classes.form} onSubmit={SubmitHandler}>
        <div className={classes.div}>
          <label htmlFor="title">Nome de usuario:</label>
          <input type="text" ref={usernameInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Informação para contato (E-mail/Telefone):</label>
          <input type="text" ref={contatoInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Senha:</label>
          <input type="text" ref={password1InputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Confirmar Senha:</label>
          <input type="text" ref={password2InputRef} />
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

export default Cadastro;
