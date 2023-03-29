package biblioteca.biblio;

import java.time.*;
import java.util.ArrayList;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private String ano;
    private String capaUrl;
    private String sinopse;
    private String username;

   private boolean disponibilidade;
   private boolean reservado;
   private LocalDate dataDevolucao;
   public ArrayList<Review> reviews = new ArrayList<>();

   private static int idcount = 1;

    public Livro(String titulo, String autor, String capaUrl, String editora, String ano, String sinopse) {
        this.id = idcount++;
        this.titulo = titulo;
        this.autor = autor;
        this.capaUrl = capaUrl;
        this.editora = editora;
        this.ano = ano;
        this.sinopse = sinopse;

        username = null;
        disponibilidade = true;
        dataDevolucao = LocalDate.now();
        reservado = true;
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setCapa(String capaUrl) {
        this.capaUrl = capaUrl;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEditora() {
        return editora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAno() {
        return ano;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void mudarDisponibilidade() {
        this.disponibilidade = !this.disponibilidade;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public LocalDate getDataDevolucao()
    {
        return dataDevolucao;
    }

    public String getUsername() {
        return username;
    }

    public String getCapaUrl() {
        return capaUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDataDevolucao(LocalDate data)
    {
        this.dataDevolucao = data;
    }

    public void mudarReserva() {
        this.reservado = !this.reservado;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void printLivro() {
        System.out.print("Titulo: " + titulo);
        System.out.print(", Autor: " + autor);
        System.out.print(", Editora: " + editora);
        System.out.print(", Ano de lançamento: " + ano);

        if (disponibilidade && reservado) {
            System.out.println(", Livro disponivel");
        } else if (!disponibilidade && reservado) {
            System.out.print(", Livro indisponivel com possibilidade de reserva");
            System.out.print(", Devoluçao esperada no dia: " + dataDevolucao);

            if (isAtrasado()) {
                System.out.println(", Livro Atrasado.");
            } else
                System.out.println(" ");

        } else if (!disponibilidade) {
            System.out.print(", Livro indisponivel sem possibilidade de reserva");
            System.out.print(", Devoluçao esperada no dia: " + dataDevolucao);

            if (isAtrasado()) {
                System.out.println(", Livro Atrasado.");
            } else
                System.out.println(" ");
        }

    }

    public boolean isAtrasado() {
        LocalDate hoje = LocalDate.now();

        return (dataDevolucao.isBefore(hoje));
    }
}
