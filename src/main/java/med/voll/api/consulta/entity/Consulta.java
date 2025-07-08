package med.voll.api.consulta.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import med.voll.api.consulta.enums.StatusConsulta;
import med.voll.api.medico.entity.Medico;
import med.voll.api.paciente.entity.Paciente;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
@Entity(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    public void cancelarConsulta() {
        this.status = StatusConsulta.CANCELADA;
    }
}
