import Livro from "./Livro";
import classes from "./ListaLivros.module.css"

function ListaLivros(props) {
  return (
    <div className={classes.div}>
      {props.livros.map((livro) => (
        <Livro 
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

export default ListaLivros;
