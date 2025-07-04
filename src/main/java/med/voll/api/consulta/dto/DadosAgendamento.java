package med.voll.api.consulta.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @JsonAlias("id_medico")
        Long idMedico,
        @NotNull
        @JsonAlias("id_paciente")
        Long idPaciente,
        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime data,
        Especialidade especialidade
) {
}
