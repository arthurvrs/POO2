import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import ListaLivros from "../componentes/ListaLivros";

function Busca() {
  const { search } = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [listaLivros, setListaLivros] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/livro/busca/${search}`)
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        setListaLivros(data);
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <p>Carregando...</p>
      </section>
    );
  }

  if (listaLivros.length === 0) {
    return (
      <section>
        <p>Nenhum livro encontrado</p>
      </section>
    );
  }
  return (
    <section>
      <div>Livros</div>
      <ListaLivros livros={listaLivros} />
    </section>
  );
}

export default Busca;
