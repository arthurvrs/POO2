import { Link, useNavigate, useParams } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import axios from "axios";
import classes from "./LivroDetalhes.module.css";
import UserContext from "../user-context";
import ListaReviews from "../componentes/ListarReviews";

function LivroDetalhes() {
  const { id } = useParams();
  const UserInfo = useContext(UserContext);
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(true);
  const [livro, setLivro] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/livro/${id}`)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        setLivro(data);
      });
  }, []);

  const devolver = (event)  => {
    event.preventDefault();
    const user = {
      username: UserInfo.username,
    };
    axios
      .post(`http://localhost:8080/livro/devolver/${id}`, user)
      .then((response) => {
        console.log(response.data);
        if (response.data === "ok") {
          navigate("/",{replace: true})
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reservar = (event) => {
    event.preventDefault();
    const user = {
      username: UserInfo.username,
    };
    axios
      .post(`http://localhost:8080/livro/reservar/${id}`, user)
      .then((response) => {
        console.log(response.data);
        if (response.data === "ok") {
          navigate("/",{replace: true})
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  if (isLoading) {
    return (
      <section>
        <p>Loading...</p>
      </section>
    );
  }

  return (
    <div>
      <div className={classes.div}>{livro.titulo}</div>
      <div className={classes.div}>
        <img className={classes.bookcover} src={livro.capaUrl} />
        <div>
          <div className={classes.basicinfo}>
            <div>Autor:</div>
            <div>{livro.autor}</div>
          </div>
          <div className={classes.basicinfo}>
            <div>editora:</div>
            <div>{livro.editora}</div>
          </div>
          <div className={classes.basicinfo}>
            <div>Ano de lançameto:</div>
            <div>{livro.autor}</div>
          </div>
        </div>
      </div>
      <div className={classes.div}>
        <div>Sinopse:</div>
        <div>{livro.sinopse}</div>
      </div>
      <div className={classes.div}>
        {UserInfo.username === livro.username ? (
          <button onClick={devolver}>Devolver</button>
        ) : livro.disponibilidade ? (
          <Link
            to={{
              pathname: `/alugar/${livro.id}`,
            }}
          >
            <button>Alugar</button>
          </Link>
        ) : livro.reservado ? (
          <button onClick={reservar}>Reservar</button>
        ) : (
          <div>Livro indisponivel</div>
        )}
      </div>
      <div className={classes.div}>Reviews:</div>
      <ListaReviews reviews={livro.reviews}/>
    </div>
  );
}

export default LivroDetalhes;
