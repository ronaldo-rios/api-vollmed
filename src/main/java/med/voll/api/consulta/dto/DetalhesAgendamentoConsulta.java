package med.voll.api.consulta.dto;

import med.voll.api.consulta.entity.Consulta;

import java.time.LocalDateTime;

public record DetalhesAgendamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DetalhesAgendamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
