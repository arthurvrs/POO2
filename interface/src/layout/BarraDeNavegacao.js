import { useContext } from "react";
import { Link } from "react-router-dom";
import UserContext from "../user-context";

import classes from "./BarraDeNavegacao.module.css";

function BarraDeNavegacao() {
  const UserInfo = useContext(UserContext);
  const { logout } = useContext(UserContext);
  console.log(UserInfo.type);
  if (UserInfo.type === "nenhum") {
    return (
      <header className={classes.header}>
        <div>Livraria</div>
        <div className={classes.dropdown}>
          <button className={classes.dropbtn}>Menu</button>
          <div className={classes.dropdowncontent}>
            <Link to="/">Home</Link>
            <Link to="/login">Login</Link>
            <Link to="/cadastro">Cadastrar-se</Link>
            <Link to="/cadastro-livro">Cadastrar Livro</Link>
            <Link to="/livros">Livros</Link>
          </div>
        </div>
        <input type="text"></input>
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
      </header>
    );
  } else {
    return (
      <header className={classes.header}>
        <div>Livraria</div>
        <div className={classes.dropdown}>
          <button className={classes.dropbtn}>Menu</button>
          <div className={classes.dropdowncontent}>
            <Link to="/">Home</Link>
            <Link to="/cadastro-livro">Cadastrar Livro</Link>
            <Link to="/livros">Livros</Link>
            <Link to="/teste">Teste</Link>
          </div>
        </div>
        <input type="text"></input>
        <nav>
          <ul>
            <li>
              <Link to="/login">{UserInfo.username}</Link>
            </li>
            <li>
              <Link to="/" onClick={logout}>
                Sair
              </Link>
            </li>
          </ul>
        </nav>
      </header>
    );
  }
}

export default BarraDeNavegacao;
