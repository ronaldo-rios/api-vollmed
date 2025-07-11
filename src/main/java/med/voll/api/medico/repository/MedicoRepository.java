package med.voll.api.medico.repository;

import med.voll.api.medico.dto.ListagemMedicosDto;
import med.voll.api.medico.entity.Medico;
import med.voll.api.medico.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT new med.voll.api.medico.dto.ListagemMedicosDto(" +
        "m.id, m.nome, m.crm, m.especialidade, m.telefone) FROM Medico m WHERE m.ativo = 1"
    )
    Page<ListagemMedicosDto> findAllIdNomeCrmEspecialidadeTelefone(Pageable pageable);

    @Query("""
            SELECT m FROM Medico m WHERE m.ativo = 1 AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c WHERE c.data = :data
            )
            ORDER BY RAND() LIMIT 1
    """)
    Medico buscarMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :id")
    Integer findAtivoById(Long id);
}
