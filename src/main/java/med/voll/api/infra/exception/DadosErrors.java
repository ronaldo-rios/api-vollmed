package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

record DadosErrors(String campo, String mensagem) {
    public DadosErrors(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}