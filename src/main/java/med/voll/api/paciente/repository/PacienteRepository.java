package med.voll.api.paciente.repository;

import med.voll.api.paciente.dto.ListagemPacientesDto;
import med.voll.api.paciente.entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT new med.voll.api.paciente.dto.ListagemPacientesDto(" +
            "p.id, p.nome, p.telefone, p.dtNascimento) FROM Paciente p"
    )
    Page<ListagemPacientesDto> findAllIdNomeTelefoneDtNascimento(Pageable pageable);
}
