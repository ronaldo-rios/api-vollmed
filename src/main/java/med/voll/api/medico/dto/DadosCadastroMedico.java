package med.voll.api.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.endereco.dto.DadosEndereco;
import med.voll.api.medico.enums.Especialidade;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
