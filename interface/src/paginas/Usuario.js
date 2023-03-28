import { useContext } from "react";

import Cliente from "../componentes/Cliente";
import Admin from "../componentes/Admin";
import UserContext from "../user-context";



function Usuario() {
  const UserInfo = useContext(UserContext);

  if (UserInfo.type === "user") {
    return <Cliente />;
  } else if (UserInfo.type === "admin") {
    return <Admin />;
  }
}

export default Usuario;
