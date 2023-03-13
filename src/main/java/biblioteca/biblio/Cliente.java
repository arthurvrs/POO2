package biblioteca.biblio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Usuario {

    public ArrayList<Livro> livrosAlugados = new ArrayList<>();
    public ArrayList<Livro> livrosReservados = new ArrayList<>();
    public ArrayList<Livro> livrosDevolvidos = new ArrayList<>();

    public Cliente(String username, String senha, String contato) {
        super(username, senha, contato);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        
        Cliente other = (Cliente) obj;
        return this.username.equals(other.username);
    }



    public void alugarLivro(Livro livro) {
        livro.mudarDisponibilidade();
        this.livrosAlugados.add(livro);
    }

    public void devolverLivro(Livro livro, Biblioteca biblioteca) {

        if (estaComLivroAlugado(livro)) {
            livrosAlugados.remove(livro);
            livro.mudarDisponibilidade();
            livrosDevolvidos.add(livro);

            buscarReserva(livro, biblioteca);
        }
    }

    public String listarLivrosAlugados() {
        StringBuilder buffer = new StringBuilder(username);
        buffer.append(" -> ");

        if (livrosAlugados.isEmpty()) {
            buffer.append("Nenhum livro alugado");
        } else {
            for (Livro livro : livrosAlugados) {
                buffer.append("Titulo: ").append(livro.getTitulo());
                buffer.append(", ");
                buffer.append(livro.isAtrasado() ? "Livro Atrasado" : "Sem Atraso");
                buffer.append(". | ");
            }
        }

        return buffer.toString();
    }

    public void reservar(Livro livro)
    {
        if(livrosAlugados.contains(livro))
        {
           System.out.println("Operacao invalida!"); 
        }
        else
        {
            if (livro.isReservado() && !livro.isDisponivel()) {
                livro.mudarReserva();
                this.livrosReservados.add(livro);
                System.out.println("Livro reservado com sucesso!");
            }
            else if (livro.isReservado() && livro.isDisponivel())
            {
                System.out.println("Opcao invalida, livro atualmente disponivel!");
            }
            else if (!livro.isDisponivel() && !livro.isReservado())
            {
                System.out.println("Livro já reservado!");
            }
        }
    }
    
    public void buscarReserva(Livro livro, Biblioteca biblioteca)
    {
        for(Cliente cliente : biblioteca.clientes)
        {
            if(!cliente.username.equals(this.username))
            {
                removerReserva(livro, cliente);
            }
        }
    }

    public void removerReserva(Livro livro, Cliente u)
    {
        if (livro.isDisponivel() && u.livrosReservados.contains(livro)) {
            livro.mudarDisponibilidade();
            LocalDate hoje = LocalDate.now();
            if(hoje.equals(livro.dataDevolucao))
            {
                livro.dataDevolucao = livro.dataDevolucao.plusDays(14);
            }
            else
            {
                livro.dataDevolucao = hoje.plusDays(14);
            }
            u.livrosAlugados.add(livro);
        }
       
        String n = livro.titulo;
       
        for(int j = 0;j < u.livrosReservados.size(); j++)
        {
            Livro aux = u.livrosReservados.get(j);
            if(aux.titulo.equals(n))
            {
                aux.mudarReserva();
                u.livrosReservados.remove(j);
                System.out.println("Livro reservado foi alugado!");
                break;
            }
        }
    }

    @Override
    public void printUsuario() {
        System.out.println(listarLivrosAlugados() + "\nContato: " + contato);
    }

    public boolean estaComLivroAlugado(Livro livro) {
        return livrosAlugados.contains(livro);
    }
}
