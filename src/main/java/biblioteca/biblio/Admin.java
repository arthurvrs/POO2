package biblioteca.biblio;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Admin extends Usuario {
  
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

    @Override
    public String printUsuario() {
        return (getUsername()+ ", Contato: " + getContato() + ", Horas: " + horasTrabalhadas + " (ADMINISTRADOR)");
    }
}