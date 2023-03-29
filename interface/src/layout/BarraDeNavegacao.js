import axios from "axios";
import { useContext, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import UserContext from "../user-context";

import classes from "./BarraDeNavegacao.module.css";

function BarraDeNavegacao() {
  const UserInfo = useContext(UserContext);
  const { logout } = useContext(UserContext);
  const navigate = useNavigate();
  console.log(UserInfo.type);

  const buscaInputRef = useRef();

  function sair() {
    const usuario = {
      username: UserInfo.username,
    };
    axios
      .post("http://localhost:8080/usuario/logout", usuario)
      .then((response) => {
        console.log(response.data);
        logout();
        navigate("/", { replace: true });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  const busca = (event) => {
    event.preventDefault();
    const searchData = buscaInputRef.current.value;
    navigate(`/busca/${searchData}`);
  };

  if (UserInfo.type === "user") {
    return (
      <header className={classes.header}>
        <div>
          <Link to="/">Biblioteca</Link>
          <div className={classes.dropdown}>
            <button className={classes.dropbtn}>Menu</button>
            <div className={classes.dropdowncontent}>
              <Link to="/">Home</Link>
              <Link to="/livros">Livros</Link>
              <Link to="/livros-alugados">Livros Alugados</Link>
              <Link to="/livros-devolvidos">Escrever Review</Link>
              <Link to="/multas">Multas</Link>
            </div>
          </div>
        </div>
        <div>
          <form onSubmit={busca}>
            <input type="text" ref={buscaInputRef} />
            <button type="submit" className={classes.button}>
              Busca
            </button>
          </form>
        </div>
        <div>
          <nav>
            <ul>
              <li>
                <Link to={"/usuario"}>{UserInfo.username}</Link>
              </li>
              <li>
                <Link to="/" onClick={sair}>
                  Sair
                </Link>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    );
  } else if (UserInfo.type === "admin") {
    return (
      <header className={classes.header}>
        <div>
          <Link to="/">Biblioteca</Link>
          <div className={classes.dropdown}>
            <button className={classes.dropbtn}>Menu</button>
            <div className={classes.dropdowncontent}>
              <Link to="/">Home</Link>
              <Link to="cadastro-admin">Cadastrar Administrador</Link>
              <Link to="/cadastro-livro">Cadastrar Livro</Link>
              <Link to="/livros">Livros</Link>
              <Link to="/usuario/listar-usuarios">Usuarios</Link>
              <Link to="/usuarios-atrasados">Usuarios Atrasados</Link>
              <Link to="/livros-atrasados">Livros Atrasados</Link>
            </div>
          </div>
        </div>
        <div>
          <form onSubmit={busca}>
            <input type="text" ref={buscaInputRef} />
            <button type="submit" className={classes.button}>
              Busca
            </button>
          </form>
        </div>
        <div>
          <nav>
            <ul>
              <li>
                <Link to={"/usuario"}>{UserInfo.username}</Link>
              </li>
              <li>
                <Link to="/" onClick={sair}>
                  Sair
                </Link>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    );
  } else {
    return (
      <header className={classes.header}>
        <div>
          <Link to="/">Biblioteca</Link>
          <div className={classes.dropdown}>
            <button className={classes.dropbtn}>Menu</button>
            <div className={classes.dropdowncontent}>
              <Link to="/">Home</Link>
              <Link to="/login">Login</Link>
              <Link to="/cadastro">Cadastrar-se</Link>
            </div>
          </div>
        </div>
        <div>
          <form onSubmit={busca}>
            <input type="text" ref={buscaInputRef} />
            <button type="submit" className={classes.button}>
              Busca
            </button>
          </form>
        </div>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/login">login</Link>
              </li>
              <li>
                <Link to="/cadastro">Cadastro</Link>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    );
  }
}

export default BarraDeNavegacao;
