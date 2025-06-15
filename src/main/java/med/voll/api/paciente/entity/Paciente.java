package med.voll.api.paciente.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.entity.Endereco;
import med.voll.api.paciente.dto.DadosCadastroPaciente;

import java.time.LocalDate;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String telefone;
    @Column(unique = true)
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dt_nascimento")
    private LocalDate dtNascimento;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.dtNascimento = dados.dtNascimento();
        this.endereco = new Endereco(dados.endereco());
    }
}
