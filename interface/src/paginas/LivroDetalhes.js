import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import classes from "./LivroDetalhes.module.css";

function LivroDetalhes() {
  const { id } = useParams();

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
      <div  className={classes.div}>
        <div>Sinopse:</div>
        <div>{livro.sinopse}</div>
      </div>
      <div className={classes.div}>
        {livro.disponibilidade ? (
          <button>Alugar</button>
        ) : livro.reservado ? (
          <button>Reservar</button>
        ) : (
          <div>Livro indisponivel</div>
        )}
      </div >
      <div  className={classes.div}>Reviews:</div>
    </div>
  );
}

export default LivroDetalhes;
