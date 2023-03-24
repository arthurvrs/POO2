import { useContext } from "react";
import UserContext from "../user-context";

function Home() {
  const UserInfo = useContext(UserContext);
  if (UserInfo.type === "user") {
    return (
      <div>
        <div>Bem vindo {UserInfo.username}</div>
      </div>
    );    
  } else if (UserInfo.type === "admin") {
    return (
      <div>
        <div>Bem vindo {UserInfo.username}</div>
      </div>
    );    
  } else{
    return (
      <div>
        <div>Por favor, faça login ou cadastre-se.</div>
      </div>
    );
  }
  
  
  
  
  
 
}

export default Home;
