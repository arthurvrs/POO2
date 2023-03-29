package biblioteca.biblio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Admin extends Usuario {
    double salario;

    private LocalDate diaPagamento;

    private LocalTime horaEntrada;

    private long horasTrabalhadas;

    public Admin(String username, String senha, String contato) {
        super(username, senha, contato);
    }

    public long getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    @Override
    public void baterPontoEntrada() {
        horaEntrada = LocalTime.now();
    }

    @Override
    public void baterPontoSaida() {
        LocalTime horaSaida = LocalTime.now();

        long horas = ChronoUnit.SECONDS.between(horaEntrada, horaSaida);
        horasTrabalhadas += horas;
    }

    public void printUsuario() {
        System.out.print(getUsername());
        System.out.print(", Contato: " + getContato());
        System.out.println(", Horas: " + horasTrabalhadas);
        // System.out.println("Dia de Pagamen: " + contato);
    }
}