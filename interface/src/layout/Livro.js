import { Link } from "react-router-dom";

import classes from "./Livro.module.css";

function Livro(props) {
  return (
    <Link
      to={{
        pathname: "/livros/livro",
        state: { titulo: "callioli" },
      }}
    >
      <div className={classes.bookcover}>
        <img src={props.capaUrl} />
        <div className={classes.titulo}>{props.titulo}</div>
      </div>
    </Link>
  );
}

export default Livro;
