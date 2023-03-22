import { createContext, useState } from "react";


const UserContext = createContext({
    username:"nenhum",
    type: "nenhum",
    login: () => {},
    logout: () => {},
});

export function UserContextProvider(props) {
    const [user, setUsername] = useState();
    const [type, setType] = useState();

    function loginHandler(username, usertype) {
        console.log("teste");
        setUsername(() => {
            return username;
        });
        setType(() => {
            return usertype;
        });
    }

    function logoutHandler () {
        setUsername(() => {
            return "nenhum";
        });
        setType(() => {
            return "nenhum";
        });
    }

    const context = {
        username: user,
        type: type,
        login: loginHandler,
        logout: logoutHandler
    }

    return (<UserContext.Provider value={context}>
        {props.children}
    </UserContext.Provider>)
}

export default UserContext;