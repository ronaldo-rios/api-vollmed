package med.voll.api.consulta.validations;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.consulta.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoComOutraConsultaMesmoHorario implements ValidaAgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamento dados) {
        var possuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (possuiOutraConsultaMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada para este mesmo horario.");
        }
    }
}
