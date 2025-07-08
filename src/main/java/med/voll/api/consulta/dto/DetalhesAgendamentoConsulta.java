package med.voll.api.consulta.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import med.voll.api.consulta.entity.Consulta;

import java.time.LocalDateTime;

public record DetalhesAgendamentoConsulta(
        Long id,
        @JsonProperty("id_medico")
        Long idMedico,
        @JsonProperty("nome_medico")
        String nomeMedico,
        @JsonProperty("id_paciente")
        Long idPaciente,
        @JsonProperty("nome_paciente")
        String nomePaciente,
        String status,
        LocalDateTime data
) {
    public DetalhesAgendamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(),
                consulta.getPaciente().getId(), consulta.getPaciente().getNome(),
                consulta.getStatus().name(), consulta.getData());
    }
}
