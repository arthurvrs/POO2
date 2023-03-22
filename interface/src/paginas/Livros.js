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
        console.log(response.data);
        return response.data;
      })
      .then((data) => {
        const livros = [];

        for (const key in data) {
          const livro = {
            id: key,
            ...data[key],
          };

          livros.push(livro);
        }

        setIsLoading(false);
        setListaLivros(livros);
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
    <section>
      <div>Livros</div>
      <ListaLivros livros={listaLivros} />
    </section>
  );
}

export default Livros;
