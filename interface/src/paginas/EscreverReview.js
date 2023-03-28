import axios from "axios";
import { useContext, useEffect, useState } from "react";

import ListaLivros2 from "../componentes/ListaLivros2";
import UserContext from "../user-context";

function EscreverReview() {
    const UserInfo = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);
  const [listaLivros, setListaLivros] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/usuario/livrosDevolvidos/${UserInfo.username}`)
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
      <div>Selecione um dos livros devolvidos para escrever o review</div>
      <ListaLivros2 livros={listaLivros} />
    </section>
  );
}

export default EscreverReview;