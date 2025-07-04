package med.voll.api.consulta.dto;

import java.time.LocalDateTime;

public record DetalhesAgendamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {}
