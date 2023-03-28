import Livro from "../componentes/Livro";
import classes from "./ListaLivros.module.css"

function ListaLivros(props) {
  return (
    <div className={classes.div}>
      {props.livros.map((livro) => (
        <Livro 
        id={livro.id}
        titulo={livro.titulo} 
        capaUrl={livro.capaUrl}
         />
      ))}
    </div>
  );
}

export default ListaLivros;
