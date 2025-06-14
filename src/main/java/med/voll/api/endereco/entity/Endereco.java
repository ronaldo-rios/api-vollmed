package med.voll.api.endereco.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.dto.DadosEndereco;

import java.util.Optional;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String cidade;
    private String uf;
    private String complemento;

    public  Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.complemento = dados.complemento();
    }

    public void update(DadosEndereco dados) {
        Optional.ofNullable(dados.logradouro()).ifPresent(v -> this.logradouro = v);
        Optional.ofNullable(dados.bairro()).ifPresent(v -> this.bairro = v);
        Optional.ofNullable(dados.cep()).ifPresent(v -> this.cep = v);
        Optional.ofNullable(dados.numero()).ifPresent(v -> this.numero = v);
        Optional.ofNullable(dados.cidade()).ifPresent(v -> this.cidade = v);
        Optional.ofNullable(dados.uf()).ifPresent(v -> this.uf = v);
        Optional.ofNullable(dados.complemento()).ifPresent(v -> this.complemento = v);
    }
}

