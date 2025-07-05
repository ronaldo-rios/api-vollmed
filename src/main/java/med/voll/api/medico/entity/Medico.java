package med.voll.api.medico.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.entity.Endereco;
import med.voll.api.medico.dto.DadosAtualizaMedico;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.enums.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private Integer ativo;
    @Embedded
    private Endereco endereco;

    public static final Integer ATIVO = 1;
    public static final Integer INATIVO = 0;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.ativo = ATIVO;
        this.endereco = new Endereco(dados.endereco());
    }

    public void update(@Valid DadosAtualizaMedico dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.endereco() != null) this.endereco.update(dados.endereco());
    }

    public void inativar() {
        this.ativo = INATIVO;
    }
}
