package med.voll.api.consulta.validations;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.entity.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidaMedicoAtivo implements ValidaAgendamentoConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamento dados) {
        if (dados.idMedico() == null) return;

        var medicoStatusAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (Objects.equals(medicoStatusAtivo, Medico.INATIVO)) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
        }
    }
}