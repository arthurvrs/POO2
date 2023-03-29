package biblioteca.biblio;

import java.util.*;

public class Biblioteca {
    
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Livro> livros = new ArrayList<> ();
    
    public Usuario login(String username, String senha)
    {
        for(Usuario usuario : usuarios)
        {
            if(usuario.getUsername().equals(username) && usuario.getSenha().equals(senha))
            {
                return usuario;
            }
        }
        return null;
    }

    public void inicializarDados() {
        usuarios.add(new Admin("admin", "admin", "admin@admin.com"));
        usuarios.add(new Cliente("user", "user", "user@user.com"));
    }
    
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

    public void listarClientes( ) {
        for (Usuario usuario : usuarios) {
            if(usuario.isCliente())
            {
                usuario.printUsuario(usuario);
            }
        }
    }

    public void listarLivros(int[] count) {
        
        for (Usuario usuario: usuarios) {
            if(usuario.isCliente())
            {
                usuario.ListarLivros(usuario, count);
                System.out.println();
            }
        }
        if(count[0] == 0)
        {
            System.out.println("Nenhum Livro Alugado!");
        }
    }

    public void listarLivrosAtrasados() {
        int count = 0;
        for(Livro livro: livros) {
            if (!livro.getDisponibilidade() && livro.isAtrasado()) {
                livro.printLivro();

                for (Usuario usuario: usuarios) {
                    if(usuario.isCliente())
                    {
                        usuario.listarLivrosAtrasados(usuario, livro);
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
        this.usuarios.add(novoUsuario);
    }

    public void listarAdministradores() {
        for (Usuario usuario : usuarios)
        {
            if(!usuario.isCliente())
            {
                usuario.printUsuario(usuario);
            }
        }
    }

    public Livro buscarLivro(String titulo) {
        Livro livro = null;
    
        for (Livro l : this.livros) {
            if (l.getTitulo().equals(titulo))
                livro = l;
        }

        return livro;
    }

    public Livro buscarLivroId(int id) {
       
        Livro livro = null;
    
        for (Livro l : this.livros) {
            if (l.getId() == id)
                livro = l;
        }

        return livro;
    }

    public Usuario buscarCliente(String username) {
        for (Usuario usuario : this.usuarios) {
            if(usuario instanceof Cliente)
            {
                if (usuario.getUsername().equals(username)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario buscarAdmin(String username) {
        for (Usuario admin : this.usuarios) {
            if(admin instanceof Admin)
            {
                if (admin.getUsername().equals(username)) {
                    return admin;
                }
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
