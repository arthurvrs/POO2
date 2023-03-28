import { useState, useEffect } from "react";
import axios from "axios";

function LivrosAtrasados() {
  const [isLoading, setIsLoading] = useState(true);
  const [listaAtrasados, setListaAtrasados] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get("http://localhost:8080/livro/get-atrasados")
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        console.log(data);
        setListaAtrasados(data);
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <p>Carregando...</p>
      </section>
    );
  }

  if (listaAtrasados.length === 0) {
    return <div>Nenhum livro atrasado</div>;
  }

  return (
    <div>
        <p>Lista de livros atrasados:</p>
      <div>
        {listaAtrasados.map((txt) => (
          <div>{txt}</div>
        ))}
      </div>
    </div>
  );
}

export default LivrosAtrasados;