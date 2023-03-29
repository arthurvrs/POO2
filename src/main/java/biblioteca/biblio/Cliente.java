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
        return this.getUsername().equals(other.getUsername());
    }

    @Override
    public void alugarLivro(Livro livro) {
        livro.mudarDisponibilidade();
        this.livrosAlugados.add(livro);
    }

    @Override
    public void devolverLivro(Livro livro, Biblioteca biblioteca) {

        if (estaComLivroAlugado(livro)) {
            livrosAlugados.remove(livro);
            livro.mudarDisponibilidade();
            livrosDevolvidos.add(livro);

            buscarReserva(livro, biblioteca);
        }
    }

    public String listarLivrosAlugados() {
        StringBuilder buffer = new StringBuilder(getUsername());
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

    @Override
    public String reservar(Livro livro) {
        if (livrosAlugados.contains(livro)) {
            return "Operacao invalida!";
        } else {
            if (livro.isReservado() && !livro.getDisponibilidade()) {
                livro.mudarReserva();
                this.livrosReservados.add(livro);
                return "ok";
            } else if (livro.isReservado() && livro.getDisponibilidade()) {
                return "Opcao invalida, livro atualmente disponivel!";
            } else if (!livro.getDisponibilidade() && !livro.isReservado()) {
                return "Livro já reservado!";
            }
        }
        return "error";
    }

    public void buscarReserva(Livro livro, Biblioteca biblioteca) {
        for (Usuario cliente : biblioteca.usuarios) {
            if (cliente instanceof Cliente) {
                if (!cliente.getUsername().equals(this.getUsername())) {
                    removerReserva(livro, cliente);
                }
            }
        }
    }

    public void removerReserva(Livro livro, Usuario u) {
        Cliente cliente = (Cliente) u;
        if (livro.getDisponibilidade() && cliente.livrosReservados.contains(livro)) {
            livro.mudarDisponibilidade();
            LocalDate hoje = LocalDate.now();
            hoje = hoje.plusDays(14);
            System.out.println(hoje);
            livro.setDataDevolucao(hoje);
            livro.setUsername(getUsername());
            cliente.livrosAlugados.add(livro);
        }

        String n = livro.getTitulo();

        for (int j = 0; j < cliente.livrosReservados.size(); j++) {
            Livro aux = cliente.livrosReservados.get(j);
            if (aux.getTitulo().equals(n)) {
                aux.mudarReserva();
                cliente.livrosReservados.remove(j);
                System.out.println("Livro reservado foi alugado!");
                break;
            }
        }
    }

    @Override
    public void printUsuario(Usuario usuarios) {
        System.out.println(listarLivrosAlugados() + "\nContato: " + getContato());
    }

    public boolean estaComLivroAlugado(Livro livro) {
        return livrosAlugados.contains(livro);
    }

    @Override
    public boolean isCliente() {
        return true;
    }

    @Override
    public void ListarLivros(Usuario usuarios, int[] count) {

        if (!livrosAlugados.isEmpty()) {
            System.out.println(listarLivrosAlugados());
            count[0]++;
        }

    }

    @Override
    public void listarLivrosAtrasados(Usuario usuario, Livro livro) {
        if (estaComLivroAlugado(livro)) {
            System.out.print("O livro está com o seguinte usuario: ");
            System.out.println(this.getUsername() + " Forma de contato: " + this.getContato());
        }
    }

    @Override
    public boolean temLivroAtrasado() {
        for (Livro l : livrosAlugados) {
            if (l.isAtrasado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Livro> pegarLivrosAlugados() {
        return livrosAlugados;
    }

    @Override
    public ArrayList<Livro> pegarLivrosDevolvidos() {
        return livrosDevolvidos;
    }
}
