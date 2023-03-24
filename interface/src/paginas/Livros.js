import axios from "axios";
import { useEffect, useState } from "react";

import ListaLivros from "../layout/ListaLivros";

function Livros() {
  const [isLoading, setIsLoading] = useState(true);
  const [listaLivros, setListaLivros] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get("http://localhost:8080/livro/getall")
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

  return (
    <section>
      <div>Livros</div>
      <ListaLivros livros={listaLivros} />
    </section>
  );
}

export default Livros;
