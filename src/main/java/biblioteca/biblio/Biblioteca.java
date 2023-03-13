package biblioteca.biblio;

import java.util.*;

public class Biblioteca {
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Livro> livros = new ArrayList<> ();

    public void criarUsuario(String username, String senha, String contato) {
        Cliente novoUsuario = new Cliente(username, senha, contato);
        this.clientes.add(novoUsuario);
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
        for (Cliente cliente : this.clientes) {
            cliente.printUsuario();
        }
    }

    public void listarLivrosAlugados() {
        int count = 0;

        for (Cliente cliente : clientes) {
            if(!cliente.livrosAlugados.isEmpty()) {
                System.out.println(cliente.listarLivrosAlugados());
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
        for(Livro livro: livros) {
            if (!livro.isDisponivel() && livro.isAtrasado()) {
                livro.printLivro();

                for (Cliente cliente: clientes) {
                    if (cliente.estaComLivroAlugado(livro)) {
                        System.out.print("O livro está com o seguinte usuario: ");
                        System.out.println(cliente.username + " Forma de contato: " + cliente.contato);
                    }
                }

                count++;
            }
        }
        if(count == 0) {
            System.out.println("Nenhum Livro Atrasado!");
        }
    }

    public void criarAdmin(String username, String senha, String contato) {
        Admin novoUsuario = new Admin(username, senha, contato);
        this.admins.add(novoUsuario);
    }

    public void listarAdministradores() {
        for (Admin admin : this.admins)
            admin.printUsuario();
    }

    public Livro buscarLivro(String titulo) {
        Livro livro = null;
    
        for (Livro l : this.livros) {
            if (l.titulo.equals(titulo))
                livro = l;
        }

        return livro;
    }

    public Cliente buscarCliente(String username) {
        for (Cliente usuario : this.clientes) {
            if (usuario.username.equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    public Admin buscarAdmin(String username) {
        for (Admin admin : this.admins) {
            if (admin.username.equals(username)) {
                return admin;
            }
        }
        return null;
    }

    public Usuario buscarUsuario(String username) {
        Usuario usuario = buscarAdmin(username);

        if (usuario == null)
            usuario = buscarCliente(username);

        return usuario;
    }
}
