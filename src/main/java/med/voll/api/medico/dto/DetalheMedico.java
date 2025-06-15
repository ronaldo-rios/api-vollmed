package med.voll.api.medico.dto;

import med.voll.api.domain.endereco.entity.Endereco;
import med.voll.api.medico.entity.Medico;
import med.voll.api.medico.enums.Especialidade;

public record DetalheMedico(
    Long id, String nome, String email,
    String crm, Especialidade especialidade, Endereco endereco
) {
    public DetalheMedico(Medico medico) {
        this(
            medico.getId(), medico.getNome(), medico.getEmail(),
            medico.getCrm(), medico.getEspecialidade(), medico.getEndereco()
        );
    }
}
