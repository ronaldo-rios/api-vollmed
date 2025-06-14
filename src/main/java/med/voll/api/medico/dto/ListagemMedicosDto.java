package med.voll.api.medico.dto;

import med.voll.api.medico.enums.Especialidade;

public record ListagemMedicosDto(Long id, String nome, String crm, Especialidade especialidade, String telefone) {
}
