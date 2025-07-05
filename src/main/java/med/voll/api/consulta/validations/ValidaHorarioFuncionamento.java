package med.voll.api.consulta.validations;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaHorarioFuncionamento implements ValidaAgendamentoConsultas {

    public void validar(DadosAgendamento dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisEncerramentoClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento");
        }
    }

}
