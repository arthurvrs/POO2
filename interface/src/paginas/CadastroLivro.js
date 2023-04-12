import axios from "axios";
import { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import classes from "../layout/input.module.css";
import ErrorMessage from "../componentes/ErrorMessage";

function CadastroLivro() {
  const tituloInputRef = useRef();
  const autorInputRef = useRef();
  const editoraInputRef = useRef();
  const anoLanInputRef = useRef();
  const capaUrlInputRef = useRef();
  const sinopseInputRef = useRef();

  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("ok");

  const SubmitHandler = (event) => {
    event.preventDefault();

    const tituloData = tituloInputRef.current.value;
    const autorData = autorInputRef.current.value;
    const editoraData = editoraInputRef.current.value;
    const anoLanData = anoLanInputRef.current.value;
    const capaUrlData = capaUrlInputRef.current.value;
    const sinopseData = sinopseInputRef.current.value;
    if (
      tituloData === "" ||
      autorData === "" ||
      editoraData === "" ||
      anoLanData === "" ||
      capaUrlData === "" ||
      sinopseData === ""
    ) {
      setErrorMessage("Campo não preenchido");
    } else {
      const livro = {
        titulo: tituloData,
        autor: autorData,
        editora: editoraData,
        ano: anoLanData,
        capaUrl: capaUrlData,
        sinopseData: sinopseData,
      };

      axios
        .post("http://localhost:8080/livro/cadastro", livro)
        .then((response) => {
          if (response.data === "ok") {
            navigate("/", { replace: true });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <card>
      <div>Cadastro de Livro</div>
      <form className={classes.form} onSubmit={SubmitHandler}>
        <div className={classes.div}>
          <label htmlFor="title">Titulo:</label>
          <input type="text" ref={tituloInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Editora:</label>
          <input type="text" ref={editoraInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Autor:</label>
          <input type="text" ref={autorInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Ano de lançamento:</label>
          <input type="text" ref={anoLanInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Capa:</label>
          <input type="text" ref={capaUrlInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Sinopse:</label>
          <textarea required rows="6" ref={sinopseInputRef} />
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
    </card>
  );
}

export default CadastroLivro;
