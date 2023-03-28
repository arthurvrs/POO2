import { useNavigate, useParams } from "react-router-dom";
import { useState, useEffect, useRef, useContext } from "react";
import axios from "axios";
import classes from "./LivroDetalhes.module.css";
import UserContext from "../user-context";

function Alugar() {
  const UserInfo = useContext(UserContext);
  const { id } = useParams();
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(true);
  const [livro, setLivro] = useState([]);
  const tempoInputRef = useRef();

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

  const SubmitHandler = (event) => {
    event.preventDefault();
    const user = {
      username: UserInfo.username,
    };
    const tempo = tempoInputRef.current.value;
    axios
      .post(`http://localhost:8080/livro/alugar/${id}/${tempo}`, user)
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
      <div>
        <form className={classes.div} onSubmit={SubmitHandler}>
          <div className={classes.basicinfo}>
            <label htmlFor="title">Tempo (dias) :</label>
            <input type="text" ref={tempoInputRef} />
            <button type="submit">Alugar</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Alugar;
