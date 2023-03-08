public abstract class Usuario {
    public String username;
    public String senha;
    public String contato;

    public Usuario (String username, String senha, String contato) {
        this.username = username;
        this.senha = senha;
        this.contato = contato;
    }

    public void alterarSenha(String senhaAtual, String senhaNova) {
        if (senhaAtual.equals(this.senha)) {
            this.senha = senhaNova;
        }
        else 
            System.out.println("Senha incorreta!");
    }

    public void alterarContato(String senha, String novoContato) {
        if (senha.equals(this.senha)) {
            this.contato = novoContato;
        }
        else 
            System.out.println("Senha incorreta!");
    }

    abstract public void printUsuario();
}