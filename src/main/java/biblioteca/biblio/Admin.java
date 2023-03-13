package biblioteca.biblio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Admin extends Usuario {
    double salario;

    LocalDate diaPagamento;

    LocalTime horaEntrada;

    long horasTrabalhadas;
    
    public Admin (String username, String senha, String contato) {
        super( username, senha, contato);
    }

    public void baterPontoEntrada() {
        horaEntrada = LocalTime.now();
    }

    public void baterPontoSaida() {
        LocalTime horaSaida = LocalTime.now();


        long horas = ChronoUnit.MINUTES.between(horaEntrada, horaSaida);
        horasTrabalhadas += horas;
    }

    @Override
    public void printUsuario() {
        System.out.print(username);
        System.out.print(", Contato: " + contato);
        System.out.println(", Horas: " + horasTrabalhadas);
        // System.out.println("Dia de Pagamen: " + contato);
    }
}