import React from "react";
import { Routes, Route } from "react-router-dom";

import Layout from "./layout/Layout";
import Alugar from "./paginas/Alugar";
import Cadastro from "./paginas/Cadastro";
import CadastroAdmin from "./paginas/CadastroAdmin";
import CadastroLivro from "./paginas/CadastroLivro";
import Home from "./paginas/Home";
import LivroDetalhes from "./paginas/LivroDetalhes";
import Livros from "./paginas/Livros";
import LivrosAlugados from "./paginas/LivrosAlugados";
import Login from "./paginas/Login";
import Usuario from "./paginas/Usuario";
import Multas from "./paginas/Multas";
import { UserContextProvider } from "./user-context";
import EscreverReview from "./paginas/EscreverReview";
import NovaReview from "./paginas/NovaReview";
import LivrosAtrasados from "./paginas/LivrosAtrasados";
import UsuariosAtrasados from "./paginas/UsuariosAtrasados";
import Busca from "./paginas/Busca";
import Senha from "./paginas/Senha";
import Contato from "./paginas/Contato";
import ListarUsuarios from "./paginas/ListarUsuarios";

function App() {
  return (
    <UserContextProvider>
      <Layout>
        <Routes>
          <Route path={"/"} element={<Home />} exact />
          <Route path={"/login"} element={<Login />} />
          <Route path={"/cadastro"} element={<Cadastro />} />
          <Route path={"/cadastro-admin"} element={<CadastroAdmin />} />
          <Route path={"/cadastro-livro"} element={<CadastroLivro />} />
          <Route path={"/livros"} element={<Livros />} />
          <Route path={"/livros/:id"} element={<LivroDetalhes />} />
          <Route path={"/livros-alugados"} element={<LivrosAlugados />} />
          <Route path={"/livros-devolvidos"} element={<EscreverReview />} />
          <Route path={"/usuario"} element={<Usuario />} />
          <Route path={"/alugar/:id"} element={<Alugar />} />
          <Route path={"/multas"} element={<Multas />} />
          <Route path={"/nova-review/:id"} element={<NovaReview />} />
          <Route path={"/livros-atrasados"} element={<LivrosAtrasados />} />
          <Route path={"/usuarios-atrasados"} element={<UsuariosAtrasados />} />
          <Route path={"/busca/:search"} element={<Busca />} />
          <Route path={"/usuario/alterar-senha"} element={<Senha />} />
          <Route path={"/usuario/alterar-contato"} element={<Contato />} />
          <Route path={"/usuario/listar-usuarios"} element={<ListarUsuarios />} />
        </Routes>
      </Layout>
    </UserContextProvider>
  );
}

export default App;
