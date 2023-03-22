import { useRef } from "react";

import classes from "../layout/input.module.css";

function CadastroLivro() {
  const tituloInputRef = useRef();
  const editoraInputRef = useRef();
  const anoLanInputRef = useRef();
  const sinopseInputRef = useRef();
  const capaURLInputRef = useRef()

  return (
    <card>
      <div>Cadastro de Livro</div>
      <form className={classes.form}>
        <div className={classes.div}>
          <label htmlFor="title">Titulo:</label>
          <input type="text" ref={tituloInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Editora:</label>
          <input type="text" ref={editoraInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Ano de lançamento:</label>
          <input type="text" ref={anoLanInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Capa:</label>
          <input type="text" ref={capaURLInputRef} />
        </div>
        <div className={classes.div}>
          <label htmlFor="title">Sinopse:</label>
          <textarea required rows='6' ref={sinopseInputRef} />
        </div>
        <div className={classes.div}>
          <button>Confirmar</button>
          <button>Cancelar</button>
        </div>
      </form>
    </card>
  );
}

export default CadastroLivro;
