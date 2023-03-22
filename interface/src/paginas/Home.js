import { useContext } from "react";
import UserContext from "../user-context";

function Home() {
  const UserInfo = useContext(UserContext);
  if (UserInfo.type === "nenhum") {
    return (
      <div>
        <div>Livraria</div>
        <div>Por favor, faça login ou cadastre-se.</div>
      </div>
    );
  } else {
    return (
      <div>
        <div>Livraria</div>
        <div>Bem vindo {UserInfo.username}</div>
      </div>
    );
  }
}

export default Home;
