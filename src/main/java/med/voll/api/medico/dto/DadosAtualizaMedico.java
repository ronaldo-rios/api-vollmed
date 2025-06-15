package med.voll.api.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco
) {
}
