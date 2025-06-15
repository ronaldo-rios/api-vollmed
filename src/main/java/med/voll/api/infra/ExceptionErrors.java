package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.endereco.dto.DadosEndereco;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> throwError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErrors>> throwError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(
            errors.stream().map(DadosErrors::new).toList()
        );
    }
}
