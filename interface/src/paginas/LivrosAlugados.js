import axios from "axios";
import { useContext, useEffect, useState } from "react";

import ListaLivros from "../componentes/ListaLivros";
import UserContext from "../user-context";

function LivrosAlugados() {
    const UserInfo = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);
  const [listaLivros, setListaLivros] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/usuario/livrosAlugados/${UserInfo.username}`)
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        console.log(data);
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
      <div>Livros Alugados</div>
      <ListaLivros livros={listaLivros} />
    </section>
  );
}

export default LivrosAlugados;
