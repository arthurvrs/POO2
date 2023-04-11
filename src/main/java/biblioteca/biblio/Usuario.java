package biblioteca.biblio;

import java.util.ArrayList;

public class Usuario {
   private String username;
   private String senha;
   private String contato;
    
    public Usuario (String username, String senha, String contato) {
        this.username = username;
        this.senha = senha;
        this.contato = contato;
    }

    public String getUsername() {
        return username;
      }
    
      public void setUsername(String username) {
        this.username = username;
      }
    
      public String getSenha() {
        return senha;
      }
    
      public void setSenha(String senha) {
        this.senha = senha;
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

    public String getContato() {
        return contato;
    }

    public boolean isCliente()
    {
        return false;
    }

    public String printUsuario() {
        return null;
    }

    public void ListarLivros(Usuario usuario, int[] count)
    {
        System.out.println("Nenhum Livro Alugado!");
    }

    public void listarLivrosAtrasados(Usuario usuario, Livro livro)
    {
        System.out.println("Nenhum Livro Atrasado!");
    }

    public boolean temLivroAtrasado() {
       return false;
    }

    public void baterPontoEntrada() {}

    public void baterPontoSaida() {}

    public void alugarLivro(Livro livro) {}

    public ArrayList<Livro> pegarLivrosAlugados() {
        return null;
    }

    public ArrayList<Livro> pegarLivrosDevolvidos() {
        return null;
    }

    public void devolverLivro(Livro livro, Biblioteca biblioteca) {}

    public String reservar(Livro livro) {
        return "usuario invalido";
    }
}