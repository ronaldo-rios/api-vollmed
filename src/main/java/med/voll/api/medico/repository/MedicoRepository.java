package med.voll.api.medico.repository;

import med.voll.api.medico.dto.ListagemMedicosDto;
import med.voll.api.medico.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT new med.voll.api.medico.dto.ListagemMedicosDto(" +
        "m.id, m.nome, m.crm, m.especialidade, m.telefone) FROM Medico m WHERE m.ativo = 1"
    )
    Page<ListagemMedicosDto> findAllIdNomeCrmEspecialidadeTelefone(Pageable pageable);

}
