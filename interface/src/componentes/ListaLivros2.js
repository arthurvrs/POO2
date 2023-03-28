import Livro2 from "../componentes/Livro2";
import classes from "./ListaLivros.module.css"

function ListaLivros2(props) {
  return (
    <div className={classes.div}>
      {props.livros.map((livro) => (
        <Livro2 
        id={livro.id}
        titulo={livro.titulo} 
        editora={livro.editora}
        capaUrl={livro.capaUrl}
        sinopse={livro.sinopse}
         />
      ))}
    </div>
  );
}

export default ListaLivros2;
