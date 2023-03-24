import React from "react";
import { Routes, Route } from "react-router-dom";

import Layout from "./layout/Layout";
import Cadastro from "./paginas/Cadastro";
import CadastroLivro from "./paginas/CadastroLivro";
import Home from "./paginas/Home";
import LivroDetalhes from "./paginas/LivroDetalhes";
import Livros from "./paginas/Livros";
import Login from "./paginas/Login";
import { UserContextProvider } from "./user-context";

function App() {
  return (
    <UserContextProvider>
      <Layout>
        <Routes>
          <Route path={"/"} element={<Home />} exact />
          <Route path={"/cadastro"} element={<Cadastro />} />
          <Route path={"/login"} element={<Login />} />
          <Route path={"/cadastro-livro"} element={<CadastroLivro />} />
          <Route path={"/livros"} element={<Livros />} />
          <Route path={"/livros/:id"} element={<LivroDetalhes />} />
          <Route path={"/usuario"} />
        </Routes>
      </Layout>
    </UserContextProvider>
  );
}

export default App;
