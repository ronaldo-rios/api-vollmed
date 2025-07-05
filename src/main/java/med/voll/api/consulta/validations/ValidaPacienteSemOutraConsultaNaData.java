package med.voll.api.consulta.validations;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.consulta.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteSemOutraConsultaNaData implements ValidaAgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamento dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var possuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(
            dados.idPaciente(), primeiroHorario, ultimoHorario
        );

        if (possuiOutraConsultaNoDia) throw new ValidacaoException("Paciente já possui consulta agendada nesse dia.");
    }
}
