import axios from "axios";
import { useContext, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

import UserContext from "../user-context";
import classes from "./multas.module.css";

function Multas() {
  const UserInfo = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);
  const [listaMultas, setListaMultas] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (UserInfo.type !== "user") {
      navigate("/", { replace: true });
    }
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/usuario/multa/${UserInfo.username}`)
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        console.log(data);
        setListaMultas(data);
      });
  }, []);

  const devolverAtrasado = (event) => {
    event.preventDefault();
    const user = {
      username: UserInfo.username,
    };
    axios
      .post(`http://localhost:8080/livro/devolver-atrasado/`, user)
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

  if (isLoading) {
    return (
      <section>
        <p>Carregando...</p>
      </section>
    );
  }

  if (listaMultas.length === 0) {
    return <div>Nenhum livro atrasado</div>;
  }

  return (
    <div>
      <div>
        {listaMultas.map((txt) => (
          <p>{txt}</p>
        ))}
      </div>
      <div>
        <div>
          pressione confirmar para pagar as multas e devolver todos os livros
          atrasados
        </div>
        <div className={classes.div}>
          <form onSubmit={devolverAtrasado}>
            <button type="submit">Confirmar</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Multas;
