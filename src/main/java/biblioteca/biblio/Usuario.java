package biblioteca.biblio;

public abstract class Usuario {
    protected String username;
    private String senha;

    protected String contato;

    public Usuario (String username, String senha, String contato) {
        this.username = username;
        this.senha = senha;
        this.contato = contato;
    }

    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (isSenhaCorreta(senhaAtual)) {
            this.senha = novaSenha;
            return true;
        }
        return false;
    }

    public boolean alterarContato(String senha, String novoContato) {
        if (isSenhaCorreta(senha)) {
            this.contato = novoContato;
            return true;
        }
        return false;
    }

    public boolean isSenhaCorreta(String senha) {
        return this.senha.equals(senha);
    }

    abstract public void printUsuario();

    public String getContato() {
        return contato;
    }
}