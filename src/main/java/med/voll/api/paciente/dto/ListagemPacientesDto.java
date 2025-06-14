package med.voll.api.paciente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ListagemPacientesDto(
        Long id,
        String nome,
        String telefone,
        @JsonProperty("data_nascimento")
        LocalDate dtNascimento
) {
}
