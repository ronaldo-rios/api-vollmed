package med.voll.api.consulta.validations;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioAntecedencia implements ValidaAgendamentoConsultas {

    public void validar(DadosAgendamento dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos <= 30) {
            throw new ValidacaoException("Consulta deve ser agendada com mínimo de 30 minutos de antecedência");
        }
    }

}
