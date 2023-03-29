import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import UserContext from "../user-context";
import classes from "./usuario.module.css";

function Cliente() {
  const UserInfo = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);
  const [usuario, setUsuario] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/usuario/${UserInfo.username}`)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        setUsuario(data);
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <p>Loading...</p>
      </section>
    );
  }

  return (
    <div>
      <div className={classes.div}>Minha Conta</div>
      <div className={classes.div}>
        <div>Nome de usuario:</div>
        <div>{usuario.username}</div>
      </div>
      <div className={classes.div}>
        <div>Forma de contato:</div>
        <div>{usuario.contato}</div>
      </div>
      <div className={classes.div}>
        <div>Tipo de conta:</div>
        <div>{UserInfo.type}</div>
      </div>
      <div className={classes.div}>
        <Link to="/usuario/alterar-senha">Mudar Senha</Link>
      </div>
      <div className={classes.div}>
        <Link to="/usuario/alterar-contato">Atualizar forma de contato</Link>
      </div>
    </div>
  );
}

export default Cliente;
