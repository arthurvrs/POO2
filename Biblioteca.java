import java.util.*;

public class Biblioteca {
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Cliente> usuarios = new ArrayList<>();
    ArrayList<Livro> livros = new ArrayList<> ();

    public void criarUsuario(String username, String senha, String contato) {
        Cliente novoUsuario = new Cliente(username, senha, contato);
        this.usuarios.add(novoUsuario);
    }

    public void cadastrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro: livros) {
            livro.printLivro();
        }
    } 

    public void listarClientes() {
        for (Cliente usuario : this.usuarios) {
            usuario.printUsuario();
        }
    }

    public void listarLivrosAlugados()
    {
        Cliente usuario = null;
        int count = 0;

        for (Cliente u : usuarios) {
            usuario = u;
            if(usuario.livrosAlugados.size() != 0)
            {
                usuario.listarLivrosAlugados();
                System.out.println();
                count ++;
            }
        }
        if(count == 0)
        {
            System.out.println("Nenhum Livro Alugado!");
        }
    }

    public void listarLivrosAtrasados() {
        int count = 0;
        for(Livro l: livros) {
            if (l.disponibilidade == false) {
                if (l.checkarAtraso() == true) {
                    l.printLivro();
                    for (Cliente u: usuarios) {
                        for (Livro liv: u.livrosAlugados) {
                            if (l.titulo.equals(liv.titulo)) {

                                System.out.print("O livro está com o seguinte usuario: ");
                                System.out.println(u.username + " Forma de contato: " + u.contato);
                                break;
                            }
                        }

                    }
                    count++;
                }
            }
        }
        if(count == 0)
        {
            System.out.println("Nenhum Livro Atrasado!");
        }
    }

    public void criarAdmin(String username, String senha, String contato) {
        Admin novoUsuario = new Admin(username, senha, contato);
        this.admins.add(novoUsuario);
    }

    public void listarConta() {
        for (Admin usuario : this.admins)
        {
            usuario.printUsuario();
            
        }
    }

    Livro buscarLivro(String titulo) {
        Livro livro = null;
    
        for (Livro l : this.livros) {
            if (l.titulo.equals(titulo))
                livro = l;
        }

        return livro;
    }

    public Cliente buscarUsuario(String username) {
        for (Cliente usuario : this.usuarios) {
            if (usuario.username.equals(username)) {
                return usuario;
            }
        }
    
        for (Cliente usuario : this.usuarios) {
            if (usuario.username.equals(username)) {
                return usuario;
            }
        }

        return null;
    }
}
