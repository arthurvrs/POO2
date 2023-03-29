import { useState, useEffect } from "react";
import axios from "axios";

function ListarUsuarios() {
  const [isLoading, setIsLoading] = useState(true);
  const [listaUsuarios, setListaUsuarios] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get("http://localhost:8080/usuario/getall")
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setIsLoading(false);
        console.log(data);
        setListaUsuarios(data);
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
    <div>
        <p>Lista de todos os usuarios:</p>
      <div>
        {listaUsuarios.map((txt) => (
          <div>{txt}</div>
        ))}
      </div>
    </div>
  );
}

export default ListarUsuarios;